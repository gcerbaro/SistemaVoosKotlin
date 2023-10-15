package br.upf.sistemadevoos.dtos

import jakarta.validation.constraints.NotNull


data class VooDTO(
    @field:NotNull(message = "Voo deve ter uma cidade de origem")
    val origem: String,
    @field:NotNull(message = "Voo deve ter uma cidade de destino")
    val destino: String,
    @field:NotNull(message = "Voo deve conter o numero de assentos")
    val nAssentos: Int
)
