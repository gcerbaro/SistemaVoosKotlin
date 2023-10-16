package br.upf.sistemadevoos.converter

import br.upf.sistemadevoos.dtos.UsuarioDTO
import br.upf.sistemadevoos.dtos.UsuarioResponseDTO
import br.upf.sistemadevoos.model.Usuario
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UsuarioConverter {
    fun toUsuarioResponseDTO (usuario: Usuario) : UsuarioResponseDTO {
        return UsuarioResponseDTO(
            id = usuario.id,
            nome = usuario.nome,
            cidade = usuario.cidade,
            email = usuario.email,
            tickets = listOf()
        )
    }

    fun toUsuario(dto: UsuarioDTO): Usuario {
        return Usuario(
            nome = dto.nome,
            cidade = dto.cidade,
            email = dto.email,
            tickets = dto.tickets,
            senha = BCryptPasswordEncoder().encode(dto.senha)
        )
    }
}