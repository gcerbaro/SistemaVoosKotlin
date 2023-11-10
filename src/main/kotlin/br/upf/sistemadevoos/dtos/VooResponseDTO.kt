package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.enums.VooStatus
import br.upf.sistemadevoos.model.AviaoPassageiros
import br.upf.sistemadevoos.model.City
import java.time.LocalDateTime

data class VooResponseDTO(
        val id: Long?,
        val origem: City,
        val destino: City,
        val aviaoID : AviaoPassageiros,
        val partida : LocalDateTime,
        val chegada : LocalDateTime,
        val embarque : LocalDateTime,
        val assentosDisp: String,
        var status : VooStatus
)
