package br.upf.sistemadevoos.dtos

data class VooResponseDTO(
    val id: Long?,
    val origem: String,
    val destino: String,
    val nAssentos: Int,
    val assentosDisp: List<Int>
)
