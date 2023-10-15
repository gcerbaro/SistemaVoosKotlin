package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.model.Usuario
import br.upf.sistemadevoos.model.Voo
import java.time.LocalDateTime

data class TicketDTO (
    val usuarioID : Usuario,
    val vooID : Voo,
    val assento : String,
    val data : LocalDateTime,
    val valor : Float
)
