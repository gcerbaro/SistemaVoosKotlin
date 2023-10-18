package br.upf.sistemadevoos.dtos

data class CityResponseDTO(
    val id : Long?,
    val nome : String,
    val latitude : Double,
    val longitude : Double
)
