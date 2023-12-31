package br.upf.sistemadevoos.repository

import br.upf.sistemadevoos.model.Voo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface VooRepository: JpaRepository<Voo, Long> {

    fun findByOrigem(origem: String, paginacao: Pageable): Page<Voo>

    fun findByDestino(destino: String, paginacao: Pageable) : Page<Voo>


}