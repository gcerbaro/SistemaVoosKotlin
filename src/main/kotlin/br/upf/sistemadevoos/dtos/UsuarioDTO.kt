package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.model.Ticket

data class UsuarioDTO(
    val nome: String,
    val cidade: String,
    val telefone: String,
    val senha: String,
    val tickets: List<Ticket> = listOf()
)
