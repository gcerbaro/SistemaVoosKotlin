package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.model.Usuario
import br.upf.sistemadevoos.model.Voo
import java.time.LocalDateTime

data class TicketDTO (
        val usuarioID : Usuario,
        val vooID : Voo,
        val assento : String,
        val partida : LocalDateTime,
        val chegada : LocalDateTime = partida,
        val embarque: LocalDateTime = partida.minusHours(2),
        val valor : Float = 0.0f
)
