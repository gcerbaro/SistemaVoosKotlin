package br.upf.sistemadevoos.converter

import br.upf.sistemadevoos.dtos.AviaoCargaDTO
import br.upf.sistemadevoos.dtos.AviaoCargaResponseDTO
import br.upf.sistemadevoos.model.AviaoCarga

class AviaoCargaConverter {

    fun toAviaoCargaResponseDTO(aviao : AviaoCarga) : AviaoCargaResponseDTO{
        return AviaoCargaResponseDTO(
                id = aviao.id,
                manufacturer = aviao.manufacturer,
                planeModel = aviao.planeModel,
                aircraftRegistration = aviao.aircraftRegistration ,
                fuelTankSize = aviao.fuelTankSize,
                avgFuelConsumption = aviao.avgFuelConsumption,
                avgSpeed = aviao.avgSpeed,
                status = aviao.status,
                cargoWeight = aviao.cargoWeight
        )
    }

    fun toAviaoCarga(dto : AviaoCargaDTO) : AviaoCarga{
        return AviaoCarga(
                manufacturer = dto.manufacturer,
                planeModel = dto.planeModel,
                aircraftRegistration = dto.aircraftRegistration ,
                fuelTankSize = dto.fuelTankSize,
                avgFuelConsumption = dto.avgFuelConsumption,
                avgSpeed = dto.avgSpeed,
                status = dto.status,
                cargoWeight = dto.cargoWeight
        )
    }
}