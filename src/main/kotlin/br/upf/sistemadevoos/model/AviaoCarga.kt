package br.upf.sistemadevoos.model

import br.upf.sistemadevoos.enums.AviaoStatus
import br.upf.sistemadevoos.interfaces.Aviao
import jakarta.persistence.*

data class AviaoCarga(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        override val manufacturer : String,
        override val  plane_model : String,
        override val  matricula : String,
        override val  fuel_tank_size : Float,
        override val  avg_fuel_consumption : Float,
        override val  avg_speed : Float,
        @Enumerated(value = EnumType.STRING)
        override val  status : AviaoStatus = AviaoStatus.AVAILABLE,
        val cargoWeight : Float
) : Aviao
