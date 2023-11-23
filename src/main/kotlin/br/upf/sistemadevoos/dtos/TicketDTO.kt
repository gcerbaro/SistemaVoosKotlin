package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.model.Usuario
import br.upf.sistemadevoos.model.Voo
import java.time.LocalDateTime
import kotlin.random.Random

data class TicketDTO (
    val usuarioID : Usuario,
    val vooID : Voo,
    val assento : String,
    val valor : Float = (4.0f * Random.nextDouble(1.0, 5.0).toFloat() ) * 100.0f
)
