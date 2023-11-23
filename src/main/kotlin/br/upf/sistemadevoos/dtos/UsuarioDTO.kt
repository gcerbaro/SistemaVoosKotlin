package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.model.Ticket

data class UsuarioDTO(
    val nome: String,
    val cidade: String,
    val email: String,
    val senha: String,
)
