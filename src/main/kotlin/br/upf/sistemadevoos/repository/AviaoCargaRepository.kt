package br.upf.sistemadevoos.repository

import br.upf.sistemadevoos.model.AviaoCarga
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface  AviaoCargaRepository : JpaRepository<AviaoCarga, Long> {

    fun findByManufacturer(aviao: String, paginacao: Pageable): Page<AviaoCarga>

    fun findByAircraftRegistration(aircraftRegistration : String) : AviaoCarga
}
