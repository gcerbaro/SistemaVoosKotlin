package br.upf.sistemadevoos.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Ticket(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        var valor: Float,
        val partida: LocalDateTime,
        val chegada: LocalDateTime,
        val embarque: LocalDateTime,
        val assento: String,

        @ManyToOne
    @JoinColumn(name = "vooID")
        val vooID: Voo,

        @ManyToOne
    @JoinColumn(name = "usuarioID")
        val usuarioID: Usuario
)
