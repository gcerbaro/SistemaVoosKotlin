package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.converter.AviaoCargaConverter
import br.upf.sistemadevoos.converter.AviaoConverter
import br.upf.sistemadevoos.dtos.AviaoCargaDTO
import br.upf.sistemadevoos.dtos.AviaoCargaResponseDTO
import br.upf.sistemadevoos.exceptions.NotFoundException
import br.upf.sistemadevoos.repository.AviaoCargaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

private const val NFMESSAGE = "Aviao não encontrado!"

class AviaoCargaService(private val repository : AviaoCargaRepository,
                        private val converter: AviaoCargaConverter,
                        private val aviaoService : AviaoService,
                        private val aviaoConverter : AviaoConverter) {
    fun listar(
            aviao : String?,
            paginacao : Pageable
    ) : Page<AviaoCargaResponseDTO>{
        val avioes = if( aviao == null){
            repository.findAll(paginacao)
        } else{
            repository.findByManufacturer(aviao, paginacao)
        }

        return avioes.map(converter::toAviaoCargaResponseDTO)
    }

    fun buscaPorId(id : Long): AviaoCargaResponseDTO {
        val aviao = repository.findById(id).orElseThrow{ NotFoundException(NFMESSAGE) }
        return converter.toAviaoCargaResponseDTO(aviao)
    }

    fun cadastrar(dto : AviaoCargaDTO) : AviaoCargaResponseDTO {

        aviaoService.cadastrar(aviaoConverter.fromAviaoCargaDTOtoAviao(dto))

        return converter.toAviaoCargaResponseDTO(
                repository.save(converter.toAviaoCarga(dto))
        )
    }

    fun atualizar(id:Long, dto: AviaoCargaDTO) : AviaoCargaResponseDTO {

        val aviao = repository.findById(id)
                .orElseThrow { NotFoundException(NFMESSAGE) }
                .copy(
                        fabricante = dto.manufacturer,
                        modelo = dto.planeModel,
                        registro = dto.aircraftRegistration,
                        tanque = dto.fuelTankSize,
                        consumo = dto.avgFuelConsumption,
                        velocidade = dto.avgSpeed,
                        condicao = dto.status,
                        cargoWeight = dto.cargoWeight
                )

        return converter.toAviaoCargaResponseDTO(repository.save(aviao))
    }

    fun deletar(id:Long){
        repository.deleteById(id)
    }

}
