package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.enums.AviaoStatus

data class AviaoCargaResponseDTO(
        val id : Long?,
        val manufacturer : String,
        val planeModel : String,
        val aircraftRegistration : String,
        val fuelTankSize : Float,
        val avgFuelConsumption : Float,
        val avgSpeed : Float,
        val status : AviaoStatus,
        val cargoWeight : Float
)
