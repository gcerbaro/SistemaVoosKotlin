package br.upf.sistemadevoos.repository

import br.upf.sistemadevoos.model.City
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CityRepository : JpaRepository<City, Long> {

}
