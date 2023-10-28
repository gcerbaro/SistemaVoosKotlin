package br.upf.sistemadevoos.repository

import br.upf.sistemadevoos.model.Aviao
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface AviaoRepository : JpaRepository<Aviao, Long> {
    fun findByManufacturer(aviao: String, paginacao: Pageable): Page<Aviao>

    fun findByAircraftRegistration(aircraftRegistration : String) : Aviao
}