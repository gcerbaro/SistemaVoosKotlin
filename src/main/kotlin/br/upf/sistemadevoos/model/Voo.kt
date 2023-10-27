package br.upf.sistemadevoos.model

import br.upf.sistemadevoos.enums.VooStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Voo(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @ManyToOne
        val origem: String,
        @ManyToOne
        val destino: String,
        val nAssentos: Int,
        @Enumerated(value = EnumType.STRING)
        var status: VooStatus,
        val partida : LocalDateTime,
        val chegada : LocalDateTime,
        val embarque : LocalDateTime,
        var assentosDisp: String
)
