package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.enums.UserTitle

data class UsuarioResponseDTO(
    val id: Long? = null,
    val nome: String,
    val cidade: String,
    val email: String,
    val title: UserTitle,
)
