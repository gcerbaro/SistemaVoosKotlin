package br.upf.sistemadevoos.repository

import br.upf.sistemadevoos.model.AviaoPassageiros
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AviaoPassageirosRepository : JpaRepository<AviaoPassageiros, Long> {
    fun findByManufacturer(aviao: String, paginacao: Pageable): Page<AviaoPassageiros>

}
