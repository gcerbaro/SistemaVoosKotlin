package br.upf.sistemadevoos.converter

import br.upf.sistemadevoos.dtos.AviaoCargaDTO
import br.upf.sistemadevoos.dtos.AviaoPassageirosDTO
import br.upf.sistemadevoos.model.Aviao

class AviaoConverter {

    fun fromAviaoPassageirosDTOtoAviao(dto : AviaoPassageirosDTO) : Aviao{
        return Aviao(
                manufacturer = dto.manufacturer,
                planeModel = dto.planeModel,
                aircraftRegistration = dto.aircraftRegistration,
                fuelTankSize = dto.fuelTankSize,
                avgFuelConsumption = dto.avgFuelConsumption,
                avgSpeed = dto.avgSpeed,
                status = dto.status
        )
    }

    fun fromAviaoCargaDTOtoAviao(dto : AviaoCargaDTO) : Aviao{
        return Aviao(
                manufacturer = dto.manufacturer,
                planeModel = dto.planeModel,
                aircraftRegistration = dto.aircraftRegistration,
                fuelTankSize = dto.fuelTankSize,
                avgFuelConsumption = dto.avgFuelConsumption,
                avgSpeed = dto.avgSpeed,
                status = dto.status
        )
    }
}