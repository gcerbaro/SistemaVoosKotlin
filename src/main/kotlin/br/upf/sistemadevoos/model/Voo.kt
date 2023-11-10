package br.upf.sistemadevoos.model

import br.upf.sistemadevoos.enums.VooStatus
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Voo(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,
        @ManyToOne
        @JoinColumn(name = "origemID")
        val origem: City,
        @ManyToOne
        @JoinColumn(name = "destinoID")
        val destino: City,
        @ManyToOne
        @JoinColumn(name = "aviaoID")
        val aviaoID : AviaoPassageiros,
        @Enumerated(value = EnumType.STRING)
        var status: VooStatus,
        val partida : LocalDateTime,
        val chegada : LocalDateTime,
        val embarque : LocalDateTime,
        var assentosDisp: String
)
