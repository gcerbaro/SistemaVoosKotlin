package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.converter.VooConverter
import br.upf.sistemadevoos.dtos.VooDTO
import br.upf.sistemadevoos.dtos.VooResponseDTO
import br.upf.sistemadevoos.enums.VooStatus
import br.upf.sistemadevoos.exceptions.NotFoundException
import br.upf.sistemadevoos.model.City
import br.upf.sistemadevoos.repository.VooRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

private const val NFMESSAGE = "Voo Não encontrado!"

@Service
class VooService (
    private val repository: VooRepository,
    private val converter: VooConverter,
    private val cityService : CityService
) {
    companion object{ //gambiarra do kotlin pq n tem static
        var jet_fuel_cost : Float = 1.1f //Preco em dolar / litro, poderia ser 3.5 dolar / galao americano
    }
    fun listarPorOrigem(
        voo: String?,
        paginacao: Pageable
    ): Page<VooResponseDTO> {
        var voos = if (voo == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByOrigem(voo, paginacao)
        }

        //atualizar informacoes no momento da listagem
        for(v in voos){
            if(LocalDateTime.now() >= v.partida){
                var novoStatus = VooStatus.DEPARTED

                if(LocalDateTime.now() >= v.chegada){
                   novoStatus = VooStatus.ARRIVED
                }

                val novoVoo = VooDTO(
                        v.origem,
                        v.destino,
                        v.nAssentos,
                        v.partida,
                        v.chegada,
                        v.embarque,
                        v.assentosDisp,
                        novoStatus
                )

                atualizar(v.id!!, novoVoo)
            }
        }

        return voos
            .map(converter::toVooResponseDTO)

    }

    fun listarPorDestino(
            voo: String?,
            paginacao: Pageable
    ): Page<VooResponseDTO> {
        var voos = if (voo == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByDestino(voo, paginacao)
        }

        //atualizar informacoes no momento da listagem
        for(v in voos){
            if(LocalDateTime.now() >= v.partida){
                var novoStatus = VooStatus.DEPARTED

                if(LocalDateTime.now() >= v.chegada){
                    novoStatus = VooStatus.ARRIVED
                }

                val novoVoo = VooDTO(
                        v.origem,
                        v.destino,
                        v.nAssentos,
                        v.partida,
                        v.chegada,
                        v.embarque,
                        v.assentosDisp,
                        novoStatus
                )

                atualizar(v.id!!, novoVoo)
            }
        }

        return voos
                .map(converter::toVooResponseDTO)
    }

    fun buscarPorId(id: Long): VooResponseDTO {
        val voo = repository.findById(id).orElseThrow { NotFoundException(NFMESSAGE) }
        return converter.toVooResponseDTO(voo)
    }

    fun cadastrar(dto: VooDTO): VooResponseDTO { //Entra como VooDTO, chegada = partida, embarque = partida
        val origem : City = cityService.buscaPorNome(dto.origem)
        val destino : City = cityService.buscaPorNome(dto.destino)
        val distance : Double = cityService.calculateDistance(origem, destino)

        val addHours : Long = 3 //define variavel temporaria para calcular quantas horas vai demorar

        dto.chegada.plusHours(addHours) //somar quantas horas vai demorar, aqui ta feito na gambiarra
        dto.embarque.minusHours(2) //por padrao o embarque comeca duas horas antes da partida

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
                nAssentos = dto.nAssentos
            )
        return converter.toVooResponseDTO(repository.save(voo))
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    /**
     * @param assento the specific seat to be removed from the availableSeat list/string
     * @param id flight's id
     * @return Unit, updates the flight's available seats list/string
     */
    fun removerAssento(assento : String, id: Long){
        var voo = repository.findById(id).orElseThrow { NotFoundException(NFMESSAGE) }

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
        var voo = repository.findById(id).orElseThrow { NotFoundException(NFMESSAGE) }

        var mulLista = voo.assentosDisp.split(" ").toMutableList()
        mulLista.add(assento)

        mulLista.sort()
        val modifiedList = mulLista.map{"$it "}.toMutableList()
        voo.assentosDisp = modifiedList.toString()

        atualizar(voo.id!!, converter.toVooDTO(voo))
    }

}