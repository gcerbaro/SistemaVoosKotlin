package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.converter.VooConverter
import br.upf.sistemadevoos.dtos.VooDTO
import br.upf.sistemadevoos.dtos.VooResponseDTO
import br.upf.sistemadevoos.exceptions.InvalidParametersException
import br.upf.sistemadevoos.exceptions.NotFoundException
import br.upf.sistemadevoos.model.Voo
import br.upf.sistemadevoos.repository.VooRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

private const val NFMESSAGE = "Voo Não encontrado!"
private const val NESEAT = "Este assento não existe ou não está disponível. Por favor, tente outro assento."
@Service
class VooService (
    private val repository: VooRepository,
    private val converter: VooConverter
) {
    fun listar(
        voo: String?,
        paginacao: Pageable
    ): Page<VooResponseDTO> {
        val voos = if (voo == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByOrigem(voo, paginacao)
        }
        return voos
            .map(converter::toVooResponseDTO)

    }

    fun buscarPorId(id: Long): VooResponseDTO {
        val voo = repository.findById(id).orElseThrow { NotFoundException(NFMESSAGE) }
        return converter.toVooResponseDTO(voo)
    }

    fun cadastrar(dto: VooDTO): VooResponseDTO {
        return converter.toVooResponseDTO(
            repository.save(converter.toVoo(dto))
        )
    }

    fun atualizar(id: Long, dto: VooDTO) : VooResponseDTO{
        val voo = repository.findById(id)
            .orElseThrow { NotFoundException (NFMESSAGE) }
            .copy(
                origem = dto.origem,
                destino = dto.destino,
                nAssentos = dto.nAssentos
            )
        return converter.toVooResponseDTO(repository.save(voo))
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun removerAssento(assento : String, id: Long){
        val voo = repository.findById(id).orElseThrow { NotFoundException(NFMESSAGE) }

        if(!voo.assentosDisp.contains(assento)){
            throw InvalidParametersException(NESEAT)
        }

        //se o assento estiver no fim da lista
        if(voo.assentosDisp.lastIndexOf(assento) == voo.assentosDisp.length-1) {
            voo.assentosDisp = voo.assentosDisp.replace(assento, "")
        } else{
            voo.assentosDisp = voo.assentosDisp.replace("$assento ", "")
        }

        atualizar(voo.id!!, converter.toVooDTO(voo))
    }

    fun adicionarAssento(id : Long, assento : String) {
        val voo = repository.findById(id).orElseThrow { NotFoundException(NFMESSAGE) }

        val mulLista = voo.assentosDisp.split(" ").toMutableList()
        mulLista.add("$assento ")

        mulLista.sort()
        var estringue = ""

        for(i in mulLista){
            estringue += "$i "
        }

        voo.assentosDisp = estringue

        atualizar(voo.id!!, converter.toVooDTO(voo))
    }

}