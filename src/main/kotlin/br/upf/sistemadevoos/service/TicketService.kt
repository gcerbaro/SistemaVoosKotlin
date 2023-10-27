package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.converter.TicketConverter
import br.upf.sistemadevoos.dtos.TicketDTO
import br.upf.sistemadevoos.dtos.TicketResponseDTO
import br.upf.sistemadevoos.exceptions.NotFoundException
import br.upf.sistemadevoos.exceptions.TooLateToRefund
import br.upf.sistemadevoos.repository.TicketRepository
import br.upf.sistemadevoos.repository.VooRepository
import org.springframework.stereotype.Service
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

private const val NFFLIGHT = "Voo Não encontrado!"
private const val NFMESSAGE = "Ticket Não encontrado!"
private const val REFUNDLATE = "Nao e possivel efetuar a devolucao. Motivo: " +
        "Devolucoes devem ser realizadas em ate 2 horas antes da partida"

@Service
class TicketService (private val ticketRepository: TicketRepository,
                     private val converter: TicketConverter,
                     private val vooService : VooService,
                     private val vooRepository: VooRepository){

    fun atualizar(id:Long, dto: TicketDTO) : TicketResponseDTO {
        val ticket = ticketRepository.findById(id)
                .orElseThrow { NotFoundException(NFMESSAGE) }
                .copy(
                        usuarioID = dto.usuarioID,
                        vooID = dto.vooID,
                        assento = dto.assento,
                        partida = dto.partida,
                        chegada =dto.chegada,
                        embarque = dto.embarque,
                        valor = dto.valor
                )
        return converter.toTicketResponseDTO(ticketRepository.save(ticket))
    }

    fun deletar(id: Long) {
        val ticket = ticketRepository.findById(id).orElseThrow { NotFoundException(NFMESSAGE) }
        val voo = vooRepository.findById(id).orElseThrow{ NotFoundException(NFFLIGHT)}

        if(LocalDateTime.now() >= voo.partida.minusHours(2)){
            throw TooLateToRefund(REFUNDLATE)
        }

        vooService.adicionarAssento(voo.id!!, ticket.assento)
        ticketRepository.deleteById(id)
    }

    fun cadastrar(dto: TicketDTO): TicketResponseDTO {
        val voo = vooRepository.findById(dto.vooID).orElseThrow{ NotFoundException(NFFLIGHT)}

        vooService.removerAssento(dto.assento, voo.id!!)

        return converter.toTicketResponseDTO(
            ticketRepository.save(converter.toTicket(dto))
        )
    }

    fun buscarPorId(id: Long): TicketResponseDTO {
        val ticket = ticketRepository.findById(id)
            .orElseThrow { NotFoundException(NFMESSAGE) }
        return converter.toTicketResponseDTO(ticket)
    }

    fun listar(
            ticket : Long?,
            paginacao : Pageable
    ): Page<TicketResponseDTO> {
        val tickets = if (ticket == null) {
            ticketRepository.findAll(paginacao)
        } else {
            ticketRepository.findByIdPageable(ticket, paginacao)
        }
        return tickets.map(converter::toTicketResponseDTO)
    }
}
