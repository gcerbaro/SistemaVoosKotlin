package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.enums.VooStatus
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime


data class VooDTO(
        @field:NotNull(message = "Voo deve ter uma cidade de origem")
    val origem: String,
        @field:NotNull(message = "Voo deve ter uma cidade de destino")
    val destino: String,
        @field:NotNull(message = "Voo deve conter o numero de assentos")
    val nAssentos: Int,
        val partida : LocalDateTime,
        val chegada : LocalDateTime = partida,
        val embarque: LocalDateTime = partida,
        val seatList : String = "",
        var status : VooStatus = VooStatus.ONTIME
)
