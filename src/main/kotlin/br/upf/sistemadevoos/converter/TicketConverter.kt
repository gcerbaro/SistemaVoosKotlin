package br.upf.sistemadevoos.converter

import br.upf.sistemadevoos.dtos.TicketDTO
import br.upf.sistemadevoos.dtos.TicketResponseDTO
import br.upf.sistemadevoos.model.Ticket
import org.springframework.stereotype.Component

@Component
class TicketConverter {
    fun toTicketResponseDTO(ticket: Ticket): TicketResponseDTO {
        return TicketResponseDTO(
            id = ticket.id,
            valor = ticket.valor,
            assento = ticket.assento,
            vooID = ticket.vooID,
            usuarioID = ticket.usuarioID
        )
    }

    fun toTicket(dto: TicketDTO) : Ticket{
        return Ticket(
                valor = dto.valor,
                assento = dto.assento,
                vooID = dto.vooID,
                usuarioID = dto.usuarioID
        )
    }

}
