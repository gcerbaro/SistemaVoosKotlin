package br.upf.sistemadevoos.controller

import br.upf.sistemadevoos.dtos.VooDTO
import br.upf.sistemadevoos.dtos.VooResponseDTO
import br.upf.sistemadevoos.model.Voo
import br.upf.sistemadevoos.service.VooService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder

@CrossOrigin("*")
@RestController
@RequestMapping("/voos")
class VooController(val service: VooService) {
    @GetMapping
    fun listar(@RequestParam(required = false) voo: String?,
               @PageableDefault(size=10) paginacao: Pageable
    ) : Page<VooResponseDTO> {
        return service.listar(voo, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId (@PathVariable id: Long): VooResponseDTO {
        return service.buscarPorId(id)
    }

    @PostMapping
    fun cadastrar (@RequestBody @Valid dto: VooDTO, uriBuilder: UriComponentsBuilder): ResponseEntity<VooResponseDTO> {
        val vooResponse = service.cadastrar(dto)
        val uri = uriBuilder.path("/voos/${vooResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(vooResponse)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody @Valid dto: VooDTO): VooResponseDTO {
        return service.atualizar(id, dto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }

}