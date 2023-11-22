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
            partida = dto.partida,
            embarque = dto.embarque,
            chegada = dto.chegada,
            nAssentos = dto.nAssentos,
            assentosDisp = dto.assentosDisp
        )
    }

    fun toVooResponseDTO(voo: Voo): VooResponseDTO{
        return VooResponseDTO(
            id = voo.id,
            origem = voo.origem,
            destino = voo.destino,
            partida = voo.partida,
            chegada = voo.chegada,
            embarque = voo.embarque,
            nAssentos = voo.nAssentos,
            assentosDisp = voo.assentosDisp
        )
    }

    fun toVooDTO(voo : Voo) : VooDTO{
        return VooDTO(
                origem = voo.origem,
                destino = voo.destino,
                embarque = voo.embarque,
                partida = voo.partida,
                chegada = voo.chegada,
                nAssentos = voo.nAssentos,
                assentosDisp = voo.assentosDisp
        )
    }
}