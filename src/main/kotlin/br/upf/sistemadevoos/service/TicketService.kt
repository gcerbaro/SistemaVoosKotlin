package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.converter.TicketConverter
import br.upf.sistemadevoos.dtos.TicketDTO
import br.upf.sistemadevoos.dtos.TicketResponseDTO
import br.upf.sistemadevoos.dtos.VooResponseDTO
import br.upf.sistemadevoos.exceptions.NotFoundException
import br.upf.sistemadevoos.model.Voo
import br.upf.sistemadevoos.repository.TicketRepository
import org.springframework.stereotype.Service

private const val NFMESSAGE = "Ticket Não encontrado!"

@Service
class TicketService (private val ticketRepository: TicketRepository,
                     private val converter: TicketConverter,
                    private val vooService: VooService){

    fun atualizar(id: Long): TicketResponseDTO {
        val ticket = ticketRepository.findById(id)
            .orElseThrow { NotFoundException(NFMESSAGE) }

        val updatedTicket = ticketRepository.save(ticket)

        return converter.toTicketResponseDTO(updatedTicket)
    }

    fun deletar(id: Long) {
        val ticket = ticketRepository.findById(id).orElseThrow{ NotFoundException(NFMESSAGE) }
        vooService.adicionarAssento(ticket.vooID.id!!, ticket.assento)
        ticketRepository.deleteById(id)
    }

    fun cadastrar(dto: TicketDTO): TicketResponseDTO {
        val voo : VooResponseDTO = vooService.buscarPorId(dto.vooID.id!!)

        vooService.removerAssento(dto.assento, dto.vooID.id)

        val ticket = dto.copy(
                usuarioID = dto.usuarioID,
                vooID = dto.vooID,
                assento = dto.assento,
                partida = voo.partida,
                chegada = voo.chegada,
                embarque = voo.embarque,
                valor = dto.valor
        )

        return converter.toTicketResponseDTO(
            ticketRepository.save(converter.toTicket(ticket))
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
