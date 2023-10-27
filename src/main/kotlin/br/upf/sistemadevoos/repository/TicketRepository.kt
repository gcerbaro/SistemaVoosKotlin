package br.upf.sistemadevoos.repository

import br.upf.sistemadevoos.model.Ticket
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TicketRepository: JpaRepository<Ticket, Long> {
    fun findByIdPageable(id : Long, paginacao: Pageable) : Page<Ticket>
}