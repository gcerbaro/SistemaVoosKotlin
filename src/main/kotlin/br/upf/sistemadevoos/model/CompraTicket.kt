package br.upf.sistemadevoos.model

import br.upf.sistemadevoos.enums.CompraStatus
import jakarta.persistence.*
import java.time.LocalDateTime

data class CompraTicket(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long? = null,
        val valor : Float,
        val dataCompra : LocalDateTime,
        val dataLimitePagamento : LocalDateTime,
        @ManyToOne
        @JoinColumn(name = "ticketID")
        val ticketID : Ticket,
        @ManyToOne
        @JoinColumn(name = "usuarioID")
        val usuarioID : Usuario,
        @Enumerated(value = EnumType.STRING)
        var status : CompraStatus,
        val nfe : String? = null
)
