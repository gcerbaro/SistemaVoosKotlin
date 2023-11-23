package br.upf.sistemadevoos.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Ticket(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val valor: Float,
    val assento: String,
    @ManyToOne
    @JoinColumn(name = "voo_id")
    val vooID: Voo,
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    val usuarioID: Usuario
)
