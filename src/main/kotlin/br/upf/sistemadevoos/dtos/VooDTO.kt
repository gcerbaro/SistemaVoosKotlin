package br.upf.sistemadevoos.dtos

import br.upf.sistemadevoos.model.Assento
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime


data class VooDTO(
        @field:NotNull(message = "Voo deve ter uma cidade de origem")
    val origem: String,
        @field:NotNull(message = "Voo deve ter uma cidade de destino")
    val destino: String,
    val partida : LocalDateTime,
    val chegada : LocalDateTime = partida.plusHours(4),
    val embarque : LocalDateTime = partida.minusHours(2),
        @field:NotNull(message = "Voo deve conter o numero de assentos")
    val nAssentos: Int,
        val assentosDisp: String = generateSeatList(nAssentos)
){
    companion object{
        private fun generateSeatList(range: Int) : String{
            return Assento.genSeatsList(range)
        }
    }
}
