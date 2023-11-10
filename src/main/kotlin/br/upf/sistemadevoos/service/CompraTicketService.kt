package br.upf.sistemadevoos.service

import br.upf.sistemadevoos.model.CompraTicket
import br.upf.sistemadevoos.repository.CompraTicketRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*

class CompraTicketService(
    private val repository : CompraTicketRepository
) {

    fun listar(
            compra : String?,
            paginacao : Pageable
    ) : Page<CompraTicket>{
        val compras = if (compra == null) {
            repository.findAll(paginacao)
        } else {
            repository.findById(compra, paginacao)
        }

        return compras
    }

    fun cadastrar(){

    }

    fun atualizar(compra : CompraTicket){
        repository.save(compra)
    }

    fun deletar(){

    }

    fun gerarNFE(compra : CompraTicket){
        println("Gerando NFE para compra ${compra.id}")
        val nfe = UUID.randomUUID().toString()
        val copia = compra.copy(nfe = nfe)

       atualizar(copia)
    }

    fun pagar(){

    }
}