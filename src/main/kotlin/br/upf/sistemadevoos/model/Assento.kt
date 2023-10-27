package br.upf.sistemadevoos.model

class Assento (classe : Char, linha : Int, coluna : Int){
    private val assentoClasse : Char = classe
    private val line : Int = linha
    private val column : Int = coluna

    override fun toString() : String {
        return "${assentoClasse}+${line}+${column}"
    }
}
