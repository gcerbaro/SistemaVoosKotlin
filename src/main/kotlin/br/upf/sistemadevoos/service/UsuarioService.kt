package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.converter.UsuarioConverter
import br.upf.sistemadevoos.dtos.UsuarioDTO
import br.upf.sistemadevoos.dtos.UsuarioResponseDTO
import br.upf.sistemadevoos.exceptions.NotFoundException
import br.upf.sistemadevoos.model.Ticket
import br.upf.sistemadevoos.repository.TicketRepository
import br.upf.sistemadevoos.repository.UsuarioRepository
import br.upf.sistemadevoos.repository.VooRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

private const val NFMESSAGE = "Usuario Não encontrado!"

@Service
class UsuarioService(
        private val repository: UsuarioRepository,
        private val converter: UsuarioConverter,
) {
    fun listar(): List<UsuarioResponseDTO>{
        return repository.findAll().map(converter::toUsuarioResponseDTO)
    }

    fun buscarPorId(id: Long): UsuarioResponseDTO{
        val usuario = repository.findById(id)
                .orElseThrow { NotFoundException(NFMESSAGE) }
        return converter.toUsuarioResponseDTO(usuario)
    }

    fun cadastrar(dto: UsuarioDTO): UsuarioResponseDTO {
        return converter.toUsuarioResponseDTO(
                repository.save(converter.toUsuario(dto))
        )
    }

    fun atualizar(id:Long, dto: UsuarioDTO) : UsuarioResponseDTO {
        val usuario = repository.findById(id)
                .orElseThrow { NotFoundException(NFMESSAGE) }
                .copy(
                        nome = dto.nome,
                        cidade = dto.cidade,
                )
        return converter.toUsuarioResponseDTO(repository.save(usuario))
    }

    fun deletar(id:Long){
        repository.deleteById(id)
    }

}