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
        val aviaoID : Long,
        val partida : LocalDateTime,
        var chegada : LocalDateTime = partida,
        var embarque: LocalDateTime = partida,
        val assentosDisp : String = "",
        var status : VooStatus = VooStatus.ONTIME
)
