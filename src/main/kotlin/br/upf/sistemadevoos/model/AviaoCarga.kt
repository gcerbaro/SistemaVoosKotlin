package br.upf.sistemadevoos.model

import br.upf.sistemadevoos.enums.AviaoStatus
import jakarta.persistence.*

data class AviaoCarga(
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
        val cargoWeight : Float
)
