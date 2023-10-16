package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.model.Ticket

data class UsuarioResponseDTO(
    val id: Long? = null,
    val nome: String,
    val cidade: String,
    val email: String,
    val tickets: List<Ticket> = listOf()
)
