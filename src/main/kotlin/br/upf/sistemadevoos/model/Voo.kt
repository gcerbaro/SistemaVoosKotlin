package br.upf.sistemadevoos.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Voo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val origem: String,
    val destino: String,
    val nAssentos: Int,
    var assentosDisp: List<Int> = listOf()
)
