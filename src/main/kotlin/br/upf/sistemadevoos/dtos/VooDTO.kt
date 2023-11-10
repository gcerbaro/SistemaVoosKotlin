package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.enums.VooStatus
import br.upf.sistemadevoos.model.AviaoPassageiros
import br.upf.sistemadevoos.model.City
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime


data class VooDTO(
        @field:NotNull(message = "Voo deve ter uma cidade de origem")
    val origem: City,
        @field:NotNull(message = "Voo deve ter uma cidade de destino")
    val destino: City,
        @field:NotNull(message = "Voo deve conter o numero de assentos")
        val aviaoID : AviaoPassageiros,
        val partida : LocalDateTime,
        var chegada : LocalDateTime = partida,
        var embarque: LocalDateTime = partida.minusHours(2),
        val assentosDisp : String = "",
        var status : VooStatus = VooStatus.ONTIME
)
