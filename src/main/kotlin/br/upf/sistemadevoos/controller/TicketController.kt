package br.upf.sistemadevoos.controller

import br.upf.sistemadevoos.dtos.TicketDTO
import br.upf.sistemadevoos.dtos.TicketResponseDTO
import br.upf.sistemadevoos.service.TicketService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@CrossOrigin("*")
@RestController
@RequestMapping("/ticket")
class TicketController(val service: TicketService){

    @GetMapping
    fun listar(): List<TicketResponseDTO> {
        return service.listar()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TicketResponseDTO {
        return service.buscarPorId(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(@RequestBody @Valid dto: TicketDTO,
                  uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TicketResponseDTO> {
        val ticketResponse = service.cadastrar(dto)
        val uri = uriBuilder.path("/ticket/${ticketResponse.id}")
            .build().toUri()
        return ResponseEntity.created(uri).body(ticketResponse)
    }

    @PutMapping("/{id}")
    @Transactional
    fun atualizar(@PathVariable id: Long,
                  @RequestBody @Valid dto: TicketDTO
    ): TicketResponseDTO {
        return service.atualizar(id)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun deletar(@PathVariable id: Long) {
        service.deletar(id)
    }
}