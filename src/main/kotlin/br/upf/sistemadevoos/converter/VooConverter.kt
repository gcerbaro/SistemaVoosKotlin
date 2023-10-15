package br.upf.sistemadevoos.converter

import br.upf.sistemadevoos.dtos.VooDTO
import br.upf.sistemadevoos.dtos.VooResponseDTO
import br.upf.sistemadevoos.model.Voo
import org.springframework.stereotype.Component

@Component
class VooConverter {
    fun toVoo(dto: VooDTO) : Voo {
        return Voo(
            origem = dto.origem,
            destino = dto.destino,
            nAssentos = dto.nAssentos,
            assentosDisp = listOf()
        )
    }

    fun toVooResponseDTO(voo: Voo): VooResponseDTO{
        return VooResponseDTO(
            id = voo.id,
            origem = voo.origem,
            destino = voo.destino,
            nAssentos = voo.nAssentos,
            assentosDisp = voo.assentosDisp
        )
    }
}