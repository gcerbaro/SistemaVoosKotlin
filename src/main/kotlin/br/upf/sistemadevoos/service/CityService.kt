package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.converter.CityConverter
import br.upf.sistemadevoos.dtos.CityDTO
import br.upf.sistemadevoos.dtos.CityResponseDTO
import br.upf.sistemadevoos.exceptions.NotFoundException
import br.upf.sistemadevoos.repository.CityRepository
import org.springframework.stereotype.Service

private val NFMESSAGE = "Cidade nao foi encontrada"

@Service
class CityService(private val repository : CityRepository,
                  private val converter : CityConverter) {

    fun listar() : List<CityResponseDTO>{
        return repository.findAll().map(converter::toCityResponseDTO)
    }

    fun buscaPorId(id : Long): CityResponseDTO{
        val city = repository.findById(id).orElseThrow{NotFoundException(NFMESSAGE)}
        return converter.toCityResponseDTO(city)
    }

    fun cadastrar(dto : CityDTO) : CityResponseDTO{
        return converter.toCityResponseDTO(
                repository.save(converter.toCity(dto))
        )
    }

    fun atualizar(id:Long, dto: CityDTO) : CityResponseDTO {
        val city = repository.findById(id)
                .orElseThrow { NotFoundException(NFMESSAGE) }
                .copy(
                        nome = dto.nome,
                        latitude = dto.latitude,
                        longitude = dto.longitude
                )
        return converter.toCityResponseDTO(repository.save(city))
    }

    fun deletar(id:Long){
        repository.deleteById(id)
    }
}