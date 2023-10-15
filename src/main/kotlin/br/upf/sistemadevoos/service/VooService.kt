package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.converter.VooConverter
import br.upf.sistemadevoos.dtos.VooDTO
import br.upf.sistemadevoos.dtos.VooResponseDTO
import br.upf.sistemadevoos.exceptions.NotFoundException
import br.upf.sistemadevoos.model.Voo
import br.upf.sistemadevoos.repository.VooRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

private const val NFMESSAGE = "Voo Não encontrado!"

@Service
class VooService (
    private val repository: VooRepository,
    private val converter: VooConverter
) {
    fun listar(
        voo: String?,
        paginacao: Pageable
    ): Page<VooResponseDTO> {
        val voos = if (voo == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByNome(voo, paginacao)
        }
        return voos
            .map(converter::toVooResponseDTO)

    }

    fun buscarPorId(id: Long): VooResponseDTO {
        val voo = repository.findById(id).orElseThrow { NotFoundException(NFMESSAGE) }
        return converter.toVooResponseDTO(voo)
    }

    fun cadastrar(dto: VooDTO): VooResponseDTO {
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
}