package br.upf.sistemadevoos.dtos

import java.time.LocalDateTime

data class TicketResponseDTO(
    val id: Long? = null,
    val usuarioID: Long,
    val vooID: Long,
    val assento: String,
    val partida : LocalDateTime,
    val chegada : LocalDateTime,
    val embarque: LocalDateTime,
    val valor: Float
)
