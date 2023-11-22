package br.upf.sistemadevoos.converter

import br.upf.sistemadevoos.dtos.AviaoPassageirosDTO
import br.upf.sistemadevoos.dtos.AviaoPassageirosResponseDTO
import br.upf.sistemadevoos.model.AviaoPassageiros

class AviaoPassageirosConverter {
    fun toAviaoPassageirosResponseDTO(aviao: AviaoPassageiros): AviaoPassageirosResponseDTO {
        return AviaoPassageirosResponseDTO(
                id = aviao.id,
                manufacturer = aviao.fabricante,
                planeModel = aviao.modelo,
                aircraftRegistration = aviao.registro,
                fuelTankSize = aviao.tanque,
                avgFuelConsumption = aviao.consumo,
                avgSpeed = aviao.velocidade,
                status = aviao.condicao,
                passagengers = aviao.passagengers,
                linhas = aviao.linhas,
                colunas = aviao.colunas,
                seatList = aviao.seatList
        )
    }

    fun toAviaoPassageiros(dto: AviaoPassageirosDTO): AviaoPassageiros {
        return AviaoPassageiros(
                fabricante = dto.manufacturer,
                modelo = dto.planeModel,
                registro = dto.aircraftRegistration,
                tanque = dto.fuelTankSize,
                consumo = dto.avgFuelConsumption,
                velocidade = dto.avgSpeed,
                condicao = dto.status,
                passagengers = dto.passagengers,
                linhas = dto.linhas,
                colunas = dto.colunas,
                seatList = dto.seatList
        )
    }

    fun toAviaoPassageirosDTO(aviao : AviaoPassageiros) : AviaoPassageirosDTO{
        return AviaoPassageirosDTO(
                aviao.fabricante,
                aviao.modelo,
                aviao.registro,
                aviao.tanque,
                aviao.consumo,
                aviao.velocidade,
                aviao.condicao,
                aviao.passagengers,
                aviao.linhas,
                aviao.colunas,
                aviao.seatList
        )
    }

}
