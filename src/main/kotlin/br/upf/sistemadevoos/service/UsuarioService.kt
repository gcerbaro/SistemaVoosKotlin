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
    private val vooRepository: VooRepository,
    private val ticketRepository : TicketRepository
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

    fun comprarTicket(userId: Long, flightId: Long): UsuarioResponseDTO {
        val flight = vooRepository.findById(flightId)
            .orElseThrow { NotFoundException("Voo nao encontrado") }

        if (flight.assentosDisp.isEmpty()) {
            throw NotFoundException("Nao ha assentos disponiveis")
        }

        val user = repository.findById(userId)
            .orElseThrow { NotFoundException("User not found") }

        val ticket = Ticket(
            valor = 100000f / flight.nAssentos, // Preco ficticio padrao
            data = LocalDateTime.now(),
            assento = flight.assentosDisp[0],
            vooID = flight,
            usuarioID = user
        )
        flight.assentosDisp.drop(1)

        val savedTicket = ticketRepository.save(ticket)
        user.tickets = user.tickets.toMutableList().apply { add(savedTicket) }

        val updatedUser = repository.save(user)

        return converter.toUsuarioResponseDTO(updatedUser)
    }

    fun cancelarTicket(ticketId: Long) {
        val ticket = ticketRepository.findById(ticketId)
            .orElseThrow { NotFoundException(NFMESSAGE) }

        val flight = ticket.vooID
        val user = ticket.usuarioID

        user.tickets.drop(user.tickets.indexOf(ticket))

        flight.assentosDisp.toMutableList().apply { add(ticket.assento) }

        ticketRepository.delete(ticket)

        vooRepository.save(flight)
        repository.save(user)
    }

}