package br.upf.sistemadevoos.model

import br.upf.sistemadevoos.enums.AviaoStatus
import jakarta.persistence.*

data class AviaoPassageiros(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        override val manufacturer: String,
        override val planeModel: String,
        override val aircraftRegistration: String,
        override val fuelTankSize: Float,
        override val avgFuelConsumption: Float,
        override val avgSpeed: Float,
        @Enumerated(value = EnumType.STRING)
        override val status: AviaoStatus = AviaoStatus.AVAILABLE,
        val passagengers: Int,
        val linhas: Int,
        val colunas: Int,
        val seatList: String
) : Aviao
