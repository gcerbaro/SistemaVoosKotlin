package br.upf.sistemadevoos.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class Voo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val origem: String,
    val destino: String,
    val partida : LocalDateTime,
    val embarque: LocalDateTime,
    val chegada: LocalDateTime,
    val nAssentos: Int,
    var assentosDisp: String
)
