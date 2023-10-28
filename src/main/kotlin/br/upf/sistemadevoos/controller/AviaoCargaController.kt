package br.upf.sistemadevoos.controller

import br.upf.sistemadevoos.dtos.AviaoCargaDTO
import br.upf.sistemadevoos.dtos.AviaoCargaResponseDTO
import br.upf.sistemadevoos.service.AviaoCargaService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/aviao/aviaocarga")
class AviaoCargaController(private val service : AviaoCargaService) {

    @GetMapping
    fun listar(@RequestParam(required = false) aviao: String?,
               @PageableDefault(size=10) paginacao: Pageable
    ) : Page<AviaoCargaResponseDTO> {
        return service.listar(aviao, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId (@PathVariable id: Long): AviaoCargaResponseDTO {
        return service.buscaPorId(id)
    }

    @PostMapping
    fun cadastrar (@RequestBody @Valid dto: AviaoCargaDTO, uriBuilder: UriComponentsBuilder): ResponseEntity<AviaoCargaResponseDTO> {
        val aviaoResponse = service.cadastrar(dto)
        val uri = uriBuilder.path("/aviao/aviaocarga/${aviaoResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(aviaoResponse)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody @Valid dto: AviaoCargaDTO): AviaoCargaResponseDTO {
        return service.atualizar(id, dto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}