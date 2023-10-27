package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.converter.AviaoPassageirosConverter
import br.upf.sistemadevoos.dtos.AviaoPassageirosDTO
import br.upf.sistemadevoos.dtos.AviaoPassageirosResponseDTO
import br.upf.sistemadevoos.exceptions.InvalidParametersException
import br.upf.sistemadevoos.exceptions.NotFoundException
import br.upf.sistemadevoos.model.Assento
import br.upf.sistemadevoos.repository.AviaoPassageirosRepository
import org.springframework.stereotype.Service
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

private const val NFMESSAGE = "Aviao nao encontrado!"
private const val INVPMESSAGE = "Parametros Invalidos: Inconsistencia entre a " +
        "quantidade de passageiros e as linhas e colunas informadas"

@Service
class AviaoPassageirosService (
        private val repository : AviaoPassageirosRepository,
        private val converter : AviaoPassageirosConverter
){

    fun listar(
            aviao : String?,
            paginacao : Pageable
    ) : Page<AviaoPassageirosResponseDTO>{
        val avioes = if( aviao == null){
            repository.findAll(paginacao)
        } else{
            repository.findByManufacturer(aviao, paginacao)
        }

        return avioes.map(converter::toAviaoPassageirosResponseDTO)
    }

    fun buscaPorId(id : Long): AviaoPassageirosResponseDTO {
        val aviao = repository.findById(id).orElseThrow{ NotFoundException(NFMESSAGE) }
        return converter.toAviaoPassageirosResponseDTO(aviao)
    }

    fun cadastrar(dto : AviaoPassageirosDTO) : AviaoPassageirosResponseDTO{

        if( (dto.colunas * dto.linhas) != dto.passagengers){
            throw InvalidParametersException(INVPMESSAGE)
        }
            dto.seatList = generateSeatList(dto)

            return converter.toAviaoPassageirosResponseDTO(
                    repository.save(converter.toAviaoPassageiros(dto))
            )
    }

    fun atualizar(id:Long, dto: AviaoPassageirosDTO) : AviaoPassageirosResponseDTO {
        if(dto.colunas * dto.linhas != dto.passagengers){
            throw InvalidParametersException(INVPMESSAGE)
        }

        val aviao = repository.findById(id)
                .orElseThrow { NotFoundException(NFMESSAGE) }
                .copy(
                    manufacturer = dto.manufacturer,
                    plane_model = dto.plane_model,
                    matricula = dto.matricula,
                    fuel_tank_size = dto.fuel_tank_size,
                    avg_fuel_consumption = dto.avg_fuel_consumption,
                    avg_speed = dto.avg_speed,
                    status = dto.status,
                    passagengers = dto.passagengers,
                    linhas = dto.linhas,
                    colunas = dto.colunas,
                    seatList = dto.seatList
            )

        return converter.toAviaoPassageirosResponseDTO(repository.save(aviao))
    }

    fun deletar(id:Long){
        repository.deleteById(id)
    }

    /**
     * @param dto a DTO for Passenger Plane
     * @return a string representing a list of seats, this format
     * is used due to the lack of support by MySql for arrays
     * it'd be too costly to switch to a NoSql DB for the moment
     */
    fun generateSeatList(dto : AviaoPassageirosDTO) : String {
        var stringDeAssentos  = ""
        val firstClass : Int = (dto.linhas * 20) / 100 //20 por cento
        val secondClass : Int = (dto.linhas * 40) / 100 // 40 por cento

        for(i in 1..dto.passagengers+1){

            for(j in 1..dto.colunas+1){
                if(i < firstClass){
                    stringDeAssentos += Assento('A', i, j).toString() + " "
                } else if(i < secondClass){
                    stringDeAssentos += Assento('B', i, j).toString() + " "
                } else{
                    stringDeAssentos += Assento('C', i, j).toString() + " "
                }
            }
        }

        stringDeAssentos = stringDeAssentos.dropLast(1) //O ultimo character sera um espaco em branco ' ', remove-o
        return stringDeAssentos
    }
}