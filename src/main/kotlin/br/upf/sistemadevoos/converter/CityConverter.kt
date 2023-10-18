package br.upf.sistemadevoos.converter

import br.upf.sistemadevoos.dtos.CityDTO
import br.upf.sistemadevoos.dtos.CityResponseDTO
import br.upf.sistemadevoos.model.City
import org.springframework.stereotype.Component

@Component
class CityConverter {
    fun toCityResponseDTO(cidade : City) : CityResponseDTO {
        return CityResponseDTO(
            id = cidade.id,
            nome = cidade.nome,
            latitude = cidade.latitude,
            longitude = cidade.longitude
        )
    }

    fun toCity(dto : CityDTO) : City{
        return City(
            nome = dto.nome,
            latitude = dto.latitude,
            longitude = dto.longitude
        )
    }
}