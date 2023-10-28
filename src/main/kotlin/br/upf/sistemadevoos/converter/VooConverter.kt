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
            aviaoID = dto.aviaoID,
            partida = dto.partida,
            chegada = dto.partida,
            embarque = dto.partida,
            status = dto.status,
            assentosDisp = dto.assentosDisp
        )
    }

    fun toVooResponseDTO(voo: Voo): VooResponseDTO{
        return VooResponseDTO(
            id = voo.id,
            origem = voo.origem,
            destino = voo.destino,
            aviaoID = voo.aviaoID,
            partida = voo.partida,
            chegada = voo.chegada,
            embarque = voo.embarque,
            status = voo.status,
            assentosDisp = voo.assentosDisp
        )
    }

    fun toVooDTO(voo : Voo) : VooDTO{
        return VooDTO(
                origem = voo.origem,
                destino = voo.destino,
                aviaoID = voo.aviaoID,
                partida = voo.partida,
                chegada = voo.partida,
                embarque = voo.partida,
                status = voo.status,
                assentosDisp = voo.assentosDisp
        )
    }
}