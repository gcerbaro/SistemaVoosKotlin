package br.upf.sistemadevoos.converter

import br.upf.sistemadevoos.dtos.AviaoCargaDTO
import br.upf.sistemadevoos.dtos.AviaoCargaResponseDTO
import br.upf.sistemadevoos.model.AviaoCarga

class AviaoCargaConverter {

    fun toAviaoCargaResponseDTO(aviao : AviaoCarga) : AviaoCargaResponseDTO{
        return AviaoCargaResponseDTO(
                id = aviao.id,
                manufacturer = aviao.fabricante,
                planeModel = aviao.modelo,
                aircraftRegistration = aviao.registro ,
                fuelTankSize = aviao.tanque,
                avgFuelConsumption = aviao.consumo,
                avgSpeed = aviao.velocidade,
                status = aviao.condicao,
                cargoWeight = aviao.cargoWeight
        )
    }

    fun toAviaoCarga(dto : AviaoCargaDTO) : AviaoCarga{
        return AviaoCarga(
                fabricante = dto.manufacturer,
                modelo = dto.planeModel,
                registro = dto.aircraftRegistration ,
                tanque = dto.fuelTankSize,
                consumo = dto.avgFuelConsumption,
                velocidade = dto.avgSpeed,
                condicao = dto.status,
                cargoWeight = dto.cargoWeight
        )
    }
}