package br.upf.sistemadevoos.repository

import br.upf.sistemadevoos.model.Ticket
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InscricaoRepository: JpaRepository<Ticket, Long> {
}