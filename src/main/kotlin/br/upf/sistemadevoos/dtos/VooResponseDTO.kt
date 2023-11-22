package br.upf.sistemadevoos.dtos

import java.time.LocalDateTime

data class VooResponseDTO(
        val id: Long?,
        val origem: String,
        val destino: String,
        val partida : LocalDateTime,
        val embarque: LocalDateTime,
        val chegada: LocalDateTime,
        val nAssentos: Int,
        val assentosDisp: String
)
