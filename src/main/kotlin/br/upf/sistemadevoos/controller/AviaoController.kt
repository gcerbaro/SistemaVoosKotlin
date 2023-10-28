package br.upf.sistemadevoos.controller

import br.upf.sistemadevoos.model.Aviao
import br.upf.sistemadevoos.service.AviaoService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/aviao")
class AviaoController(private val service : AviaoService) {

    @GetMapping
    fun listar(@RequestParam(required = false) aviao: String?,
               @PageableDefault(size=10) paginacao: Pageable
    ) : Page<Aviao>{
        return service.listar(aviao, paginacao)
    }

    @GetMapping
    fun cadastrar (@RequestBody @Valid aviao: Aviao,  uriBuilder: UriComponentsBuilder): ResponseEntity<Aviao>{
        val aviaoResponse = service.cadastrar(aviao)
        val uri = uriBuilder.path("/aviao/${aviaoResponse.aircraftRegistration}").build().toUri()
        return ResponseEntity.created(uri).body(aviaoResponse)
    }

    @GetMapping("/{aircraftRegistration}")
    fun buscaPorMatricula(@PathVariable aircraftRegistration : String) : Aviao{
        return service.buscaPorAircraftRegistratrion(aircraftRegistration)
    }
}