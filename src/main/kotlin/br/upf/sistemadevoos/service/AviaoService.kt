package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.model.Aviao
import br.upf.sistemadevoos.repository.AviaoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class AviaoService(
        private val repository : AviaoRepository) {

    fun listar(
            aviao : String?,
            paginacao : Pageable
    ) : Page<Aviao> {

        val avioes = if( aviao == null){
            repository.findAll(paginacao)
        } else{
            repository.findByManufacturer(aviao, paginacao)
        }

        return avioes
    }

    fun cadastrar(aviao : Aviao) : Aviao{
       return repository.save(aviao)
    }

    fun buscaPorAircraftRegistratrion(aircraftRegistration : String): Aviao {
        return repository.findByAircraftRegistration(aircraftRegistration)
    }
}