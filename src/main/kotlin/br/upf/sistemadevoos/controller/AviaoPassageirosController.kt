package br.upf.sistemadevoos.controller

import br.upf.sistemadevoos.dtos.AviaoPassageirosDTO
import br.upf.sistemadevoos.dtos.AviaoPassageirosResponseDTO
import br.upf.sistemadevoos.service.AviaoPassageirosService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/aviaopassageiro")
class AviaoPassageirosController(private val service : AviaoPassageirosService) {

    @GetMapping
    fun listar(@RequestParam(required = false) aviao: String?,
               @PageableDefault(size=10) paginacao: Pageable
    ) : Page<AviaoPassageirosResponseDTO> {
        return service.listar(aviao, paginacao)
    }

    @GetMapping("/{id}")
    fun buscarPorId (@PathVariable id: Long): AviaoPassageirosResponseDTO {
        return service.buscaPorId(id)
    }

    @PostMapping
    fun cadastrar (@RequestBody @Valid dto: AviaoPassageirosDTO, uriBuilder: UriComponentsBuilder): ResponseEntity<AviaoPassageirosResponseDTO> {
        val aviaoResponse = service.cadastrar(dto)
        val uri = uriBuilder.path("/aviao/aviaopassageiro/${aviaoResponse.id}").build().toUri()
        return ResponseEntity.created(uri).body(aviaoResponse)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody @Valid dto: AviaoPassageirosDTO): AviaoPassageirosResponseDTO {
        return service.atualizar(id, dto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}