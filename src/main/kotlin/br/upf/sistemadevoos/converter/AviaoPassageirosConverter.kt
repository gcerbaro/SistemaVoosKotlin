package br.upf.sistemadevoos.converter

import br.upf.sistemadevoos.dtos.AviaoPassageirosDTO
import br.upf.sistemadevoos.dtos.AviaoPassageirosResponseDTO
import br.upf.sistemadevoos.model.AviaoPassageiros

class AviaoPassageirosConverter {
    fun toAviaoPassageirosResponseDTO(aviao: AviaoPassageiros): AviaoPassageirosResponseDTO {
        return AviaoPassageirosResponseDTO(
                id = aviao.id,
                manufacturer = aviao.manufacturer,
                planeModel = aviao.planeModel,
                aircraftRegistration = aviao.aircraftRegistration,
                fuelTankSize = aviao.fuelTankSize,
                avgFuelConsumption = aviao.avgFuelConsumption,
                avgSpeed = aviao.avgSpeed,
                status = aviao.status,
                passagengers = aviao.passagengers,
                linhas = aviao.linhas,
                colunas = aviao.colunas,
                seatList = aviao.seatList
        )
    }

    fun toAviaoPassageiros(dto: AviaoPassageirosDTO): AviaoPassageiros {
        return AviaoPassageiros(
                manufacturer = dto.manufacturer,
                planeModel = dto.planeModel,
                aircraftRegistration = dto.aircraftRegistration,
                fuelTankSize = dto.fuelTankSize,
                avgFuelConsumption = dto.avgFuelConsumption,
                avgSpeed = dto.avgSpeed,
                status = dto.status,
                passagengers = dto.passagengers,
                linhas = dto.linhas,
                colunas = dto.colunas,
                seatList = dto.seatList
        )
    }

    fun toAviaoPassageirosDTO(aviao : AviaoPassageiros) : AviaoPassageirosDTO{
        return AviaoPassageirosDTO(
                aviao.manufacturer,
                aviao.planeModel,
                aviao.aircraftRegistration,
                aviao.fuelTankSize,
                aviao.avgFuelConsumption,
                aviao.avgSpeed,
                aviao.status,
                aviao.passagengers,
                aviao.linhas,
                aviao.colunas,
                aviao.seatList
        )
    }

}
