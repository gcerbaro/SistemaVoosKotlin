package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.converter.TicketConverter
import br.upf.sistemadevoos.dtos.TicketDTO
import br.upf.sistemadevoos.dtos.TicketResponseDTO
import br.upf.sistemadevoos.exceptions.NotFoundException
import br.upf.sistemadevoos.repository.TicketRepository

private const val NFMESSAGE = "Ticket Não encontrado!"

class TicketService (private val ticketRepository: TicketRepository,
                     private val converter: TicketConverter){

    fun atualizar(id: Long): TicketResponseDTO {
        val ticket = ticketRepository.findById(id)
            .orElseThrow { NotFoundException(NFMESSAGE) }

        val updatedTicket = ticketRepository.save(ticket)

        return converter.toTicketResponseDTO(updatedTicket)
    }

    fun deletar(id: Long) {
        ticketRepository.deleteById(id)
    }

    fun cadastrar(dto: TicketDTO): TicketResponseDTO {
        return converter.toTicketResponseDTO(
            ticketRepository.save(converter.toTicket(dto))
        )
    }

    fun buscarPorId(id: Long): TicketResponseDTO {
        val ticket = ticketRepository.findById(id)
            .orElseThrow { NotFoundException(NFMESSAGE) }
        return converter.toTicketResponseDTO(ticket)
    }

    fun listar(): List<TicketResponseDTO> {
        return ticketRepository.findAll().map(converter::toTicketResponseDTO)
    }
}
