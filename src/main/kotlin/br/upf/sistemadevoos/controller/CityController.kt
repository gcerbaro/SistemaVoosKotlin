package br.upf.sistemadevoos.controller

import br.upf.sistemadevoos.dtos.CityDTO
import br.upf.sistemadevoos.dtos.CityResponseDTO
import br.upf.sistemadevoos.service.CityService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@CrossOrigin("*")
@Controller
@RequestMapping("/cidades")
class CityController(private val service : CityService) {
    @GetMapping
    fun listar() : List<CityResponseDTO>{
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id : Long) : CityResponseDTO{
        return service.buscaPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody @Valid dto: CityDTO,
                  uriBuilder : UriComponentsBuilder) : ResponseEntity<CityResponseDTO>{
        val cityResponse = service.cadastrar(dto)
        val uri = uriBuilder.path("/cidades/${cityResponse.id}").build().toUri()

        return ResponseEntity.created(uri).body(cityResponse)
    }

    @PutMapping("/{id}")
    @Transactional
    fun atualizar(@PathVariable id: Long,
                  @RequestBody @Valid dto: CityDTO
    ): CityResponseDTO {
        return service.atualizar(id, dto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}