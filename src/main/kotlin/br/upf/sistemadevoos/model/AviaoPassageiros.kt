package br.upf.sistemadevoos.model

import br.upf.sistemadevoos.enums.AviaoStatus
import jakarta.persistence.*

data class AviaoPassageiros(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        val fabricante : String,
        val  modelo : String,
        val  registro : String,
        val  tanque : Float,
        val  consumo : Float,
        val  velocidade : Float,
        @Enumerated(value = EnumType.STRING)
        val  condicao : AviaoStatus = AviaoStatus.AVAILABLE,
        val passagengers: Int,
        val linhas: Int,
        val colunas: Int,
        val seatList: String
) : Aviao(manufacturer = fabricante,
        planeModel = modelo,
        aircraftRegistration = registro,
        fuelTankSize = tanque,
        avgFuelConsumption = consumo,
        avgSpeed = velocidade,
        status = condicao)