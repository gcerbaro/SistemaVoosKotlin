package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.enums.AviaoStatus

data class AviaoPassageirosDTO (
        val manufacturer : String,
        val  planeModel : String,
        val  aircraftRegistration : String,
        val  fuelTankSize : Float,
        val  avgFuelConsumption : Float,
        val  avgSpeed : Float,
        var status : AviaoStatus,
        val passagengers : Int,
        val linhas : Int,
        val colunas : Int,
        var seatList : String = ""
)