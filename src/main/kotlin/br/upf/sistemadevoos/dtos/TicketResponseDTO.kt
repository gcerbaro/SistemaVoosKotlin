package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.model.Usuario
import br.upf.sistemadevoos.model.Voo
import java.time.LocalDateTime

data class TicketResponseDTO(
    val id: Long? = null,
    val usuarioID: Usuario,
    val vooID: Voo,
    val assento: Int,
    val data: LocalDateTime,
    val valor: Float
)
