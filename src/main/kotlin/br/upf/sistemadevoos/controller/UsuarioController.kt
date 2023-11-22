package br.upf.sistemadevoos.controller

import br.upf.sistemadevoos.dtos.UsuarioDTO
import br.upf.sistemadevoos.dtos.UsuarioResponseDTO
import br.upf.sistemadevoos.model.Voo
import br.upf.sistemadevoos.service.UsuarioService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/usuarios")
class UsuarioController (val service: UsuarioService){

    @GetMapping
    fun listar(): List<UsuarioResponseDTO> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): UsuarioResponseDTO {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody @Valid dto: UsuarioDTO,
                  uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UsuarioResponseDTO> {
        val userResponse = service.cadastrar(dto)
        val uri = uriBuilder.path("/usuarios/${userResponse.id}")
            .build().toUri()
        return ResponseEntity.created(uri).body(userResponse)
    }

    @PutMapping("/{id}")
    @Transactional
    fun atualizar(@PathVariable id: Long,
                  @RequestBody @Valid dto: UsuarioDTO
    ): UsuarioResponseDTO {
        return service.atualizar(id, dto)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }

}