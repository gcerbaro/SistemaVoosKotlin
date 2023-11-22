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
            partida = ticket.partida,
            chegada = ticket.chegada,
            embarque = ticket.embarque,
            assento = ticket.assento,
            vooID = ticket.vooID,
            usuarioID = ticket.usuarioID
        )
    }

    fun toTicket(dto: TicketDTO) : Ticket{
        return Ticket(
                valor = dto.valor,
                partida = dto.partida!!,
                chegada = dto.chegada!!,
                embarque = dto.embarque!!,
                assento = dto.assento,
                vooID = dto.vooID,
                usuarioID = dto.usuarioID
        )
    }

}
