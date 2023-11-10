package br.upf.sistemadevoos.repository

import br.upf.sistemadevoos.model.CompraTicket
import org.springframework.data.jpa.repository.JpaRepository

interface CompraTicketRepository : JpaRepository<CompraTicket, Long> {

}
