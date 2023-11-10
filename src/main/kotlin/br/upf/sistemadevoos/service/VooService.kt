package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.converter.AviaoPassageirosConverter
import br.upf.sistemadevoos.converter.VooConverter
import br.upf.sistemadevoos.dtos.AviaoPassageirosDTO
import br.upf.sistemadevoos.dtos.VooDTO
import br.upf.sistemadevoos.dtos.VooResponseDTO
import br.upf.sistemadevoos.enums.AviaoStatus
import br.upf.sistemadevoos.enums.VooStatus
import br.upf.sistemadevoos.exceptions.NotFoundException
import br.upf.sistemadevoos.exceptions.UnavailablePlaneException
import br.upf.sistemadevoos.exceptions.UnsuitablePlaneException
import br.upf.sistemadevoos.model.AviaoPassageiros
import br.upf.sistemadevoos.model.City
import br.upf.sistemadevoos.model.Voo
import br.upf.sistemadevoos.repository.AviaoPassageirosRepository
import br.upf.sistemadevoos.repository.CityRepository
import br.upf.sistemadevoos.repository.VooRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

private const val NFMESSAGE = "Voo Não encontrado!"
private const val NFPLANE = "Avião Não encontrado!"
private const val NFCITY = "Cidade não encontrada!"
private const val NOTENOUGHFUEL = "Este avião não possui combustível suficiente para esta viagem"

@Service
class VooService (
    private val repository: VooRepository,
    private val converter: VooConverter,
    private val cityService : CityService,
    private val cityRepository : CityRepository,
    private val aviaoService : AviaoPassageirosService,
    private val aviaoConverter : AviaoPassageirosConverter,
    private val aviaoRepository : AviaoPassageirosRepository
) {
    companion object{
        var jetFuelCost : Float = 1.1f //Preco em dolar / litro, poderia ser 3.5 dolar / galao americano
    }

    fun listarPorOrigem(
        voo: String?,
        paginacao: Pageable
    ): Page<VooResponseDTO> {
        val voos = if (voo == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByOrigem(voo, paginacao)
        }

        //atualizar informacoes no momento da listagem
        for(v in voos){
            atualizarStatusVoo(v)
        }

        return voos
            .map(converter::toVooResponseDTO)

    }

    fun listarPorDestino(
            voo: String?,
            paginacao: Pageable
    ): Page<VooResponseDTO> {
        val voos = if (voo == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByDestino(voo, paginacao)
        }

        //atualizar informacoes no momento da listagem
        for(v in voos){
            atualizarStatusVoo(v)
        }

        return voos
                .map(converter::toVooResponseDTO)
    }

    fun buscarPorId(id: Long): VooResponseDTO {
        val voo = repository.findById(id).orElseThrow { NotFoundException(NFMESSAGE) }
        return converter.toVooResponseDTO(voo)
    }

    fun cadastrar(dto: VooDTO): VooResponseDTO { //Entra como VooDTO, chegada = partida, embarque = partida
        val origem : City = cityRepository.findById(dto.origem.id!!).orElseThrow { NotFoundException(NFCITY) }
        val destino : City = cityRepository.findById(dto.destino.id!!).orElseThrow { NotFoundException(NFCITY) }
        val aviao : AviaoPassageiros = aviaoRepository.findById(dto.aviaoID.id!!).orElseThrow { NotFoundException(NFPLANE) }
        val distance : Double = cityService.calculateDistance(origem, destino)

        if(aviao.status != AviaoStatus.AVAILABLE){
            throw UnavailablePlaneException("Aviao Indisponivel")
        }

        val addHours : Long = (distance / aviao.avgSpeed).toLong()
        val requiredFuel = aviao.avgFuelConsumption * addHours

        if(requiredFuel > aviao.fuelTankSize){
            throw UnsuitablePlaneException(NOTENOUGHFUEL)
        }

        val aviaodto : AviaoPassageirosDTO = aviaoConverter.toAviaoPassageirosDTO(aviao)
        aviaodto.status = AviaoStatus.UNAVAILABLE

        aviaoService.atualizar(aviao.id!!, aviaodto)

        dto.chegada = dto.embarque.plusHours(addHours)


        return converter.toVooResponseDTO(
            repository.save(converter.toVoo(dto))
        )
    }

    fun atualizar(id: Long, dto: VooDTO) : VooResponseDTO{
        val voo = repository.findById(id)
            .orElseThrow { NotFoundException (NFMESSAGE) }
            .copy(
                origem = dto.origem,
                destino = dto.destino,
                aviaoID = dto.aviaoID,
                status = dto.status,
                partida = dto.partida,
                chegada = dto.chegada,
                embarque = dto.embarque,
                assentosDisp = dto.assentosDisp
            )
        return converter.toVooResponseDTO(repository.save(voo))
    }

    fun deletar(id: Long) {

        val aviaodto : AviaoPassageirosDTO = aviaoConverter.toAviaoPassageirosDTO(
                aviaoRepository.findById(id).orElseThrow { NotFoundException(NFPLANE) }
        )

        aviaodto.status = AviaoStatus.AVAILABLE

        aviaoService.atualizar(id, aviaodto)
        repository.deleteById(id)
    }

    /**
     * @param assento the specific seat to be removed from the availableSeat list/string
     * @param id flight's id
     * @return Unit, updates the flight's available seats list/string
     */
    fun removerAssento(assento : String, id: Long){
        val voo = repository.findById(id).orElseThrow { NotFoundException(NFMESSAGE) }

        if(voo.assentosDisp.lastIndexOf(assento) == voo.assentosDisp.length-1) {
            voo.assentosDisp.replace(assento, "")
        } else{
            voo.assentosDisp.replace("$assento ", "")
        }

        atualizar(voo.id!!, converter.toVooDTO(voo))
    }

    /**
     * @param id flight's id
     * @param assento the Seat to be inserted
     * @return Unit, updates the flight's available seat list with a newly
     * available one
     */
    fun adicionarAssento(id : Long, assento : String) {
        val voo = repository.findById(id).orElseThrow { NotFoundException(NFMESSAGE) }

        val mulLista = voo.assentosDisp.split(" ").toMutableList()
        mulLista.add(assento)

        mulLista.sort()
        val modifiedList = mulLista.map{"$it "}.toMutableList()
        voo.assentosDisp = modifiedList.toString()

        atualizar(voo.id!!, converter.toVooDTO(voo))
    }

    /**
     * @param v Is a flight to have its STATUS property checked
     * Purpose: when making a GET request, updates the info
     * on a flight's status whether it has arrived, has departed
     * or remains ontime.
     * @return Unit, is basically a void function
     */
    fun atualizarStatusVoo(v : Voo){
        if(LocalDateTime.now() >= v.partida){
            var novoStatus = VooStatus.DEPARTED

            if(LocalDateTime.now() >= v.chegada){
                novoStatus = VooStatus.ARRIVED
                val aviao : AviaoPassageiros = aviaoRepository.findById(v.aviaoID.id!!).orElseThrow { NotFoundException(NFPLANE) }
                val aviaodto : AviaoPassageirosDTO = aviaoConverter.toAviaoPassageirosDTO(aviao)
                aviaodto.status = AviaoStatus.AVAILABLE

                aviaoService.atualizar(aviao.id!!, aviaodto)
            }

            val novoVoo = converter.toVooDTO(v)
            novoVoo.status = novoStatus

            atualizar(v.id!!, novoVoo)
        }
    }

    fun calcularCustoVoo(v: Voo) : Float{
        val horas = v.chegada - v.partida
        val consumo = v.aviaoID.avgFuelConsumption * horas

        return consumo * jetFuelCost
    }

    fun custoPorPassageiro(v: Voo) : Float{
        return calcularCustoVoo(v) / v.aviaoID.passagengers
    }
}
