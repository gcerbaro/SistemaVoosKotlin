package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.enums.VooStatus
import java.time.LocalDateTime

data class VooResponseDTO(
        val id: Long?,
        val origem: String,
        val destino: String,
        val aviaoID : Long,
        val partida : LocalDateTime,
        val chegada : LocalDateTime,
        val embarque : LocalDateTime,
        val assentosDisp: String,
        var status : VooStatus
)
