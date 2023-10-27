package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.converter.CityConverter
import br.upf.sistemadevoos.dtos.CityDTO
import br.upf.sistemadevoos.dtos.CityResponseDTO
import br.upf.sistemadevoos.exceptions.NotFoundException
import br.upf.sistemadevoos.model.City
import br.upf.sistemadevoos.repository.CityRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import kotlin.math.*

private const val NFMESSAGE = "Cidade nao foi encontrada"

@Service
class CityService(private val repository : CityRepository,
                  private val converter : CityConverter) {

    fun listar(
            cidade: String?,
            paginacao: Pageable
    ) : Page<CityResponseDTO>{
        val city = if (cidade == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByNamePageable(cidade, paginacao)
        }
        return city
                .map(converter::toCityResponseDTO)

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

    private fun degreeToRadian(degree : Double) : Double {
        return ( (degree * Math.PI ) / 180.0)
    }

    private fun radianToDegree(radian : Double) : Double {
        return ((radian * 180.0) / Math.PI)
    }

    fun buscaPorNome(nome : String) : City{
        return repository.findByName(nome)
    }

    /**
     * @param origem the city's id as starting point
     * @param destino the city's id as ending point
     * @return the distance (in km) between the two parameter cities on the globe
     * for the miles option see the val eartRadius line comment
     */
    fun calculateDistance(origem : City, destino : City) : Double{

        val lat1 = degreeToRadian(origem.latitude)
        val lon1 = degreeToRadian(origem.longitude)
        val lat2 = degreeToRadian(destino.latitude)
        val lon2 = degreeToRadian(destino.longitude)

        val dLat = lat2 - lat1
        val dLon = lon2 - lon1

        val a = sin(dLat / 2).pow(2) + cos(lat1) * cos(lat2) * sin(dLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))

        val earthRadius = 6371.0 // Raio em km, para milha: 3958.8
        return earthRadius * c
    }
}