package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.enums.AviaoStatus

data class AviaoPassageirosResponseDTO(
        val id: Long?,
        val manufacturer : String,
        val  planeModel : String,
        val  aircraftRegistration : String,
        val  fuelTankSize : Float,
        val  avgFuelConsumption : Float,
        val  avgSpeed : Float,
        val  status : AviaoStatus,
        val passagengers : Int,
        val linhas : Int,
        val colunas : Int,
        val seatList : String
)