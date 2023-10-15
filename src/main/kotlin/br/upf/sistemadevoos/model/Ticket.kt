package br.upf.sistemadevoos.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Ticket(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val valor: Float,
    val data: LocalDateTime,
    val assento: Int,
    @OneToOne
    val vooID: Voo,
    @OneToOne
    val usuarioID: Usuario
)
