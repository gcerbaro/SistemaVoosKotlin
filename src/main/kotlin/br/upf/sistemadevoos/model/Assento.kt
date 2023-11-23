package br.upf.sistemadevoos.model

class Assento (classe : Char, linha : Int){
    private val assentoClasse : Char = classe
    private val line : Int = linha

    override fun toString() : String {
        return "${assentoClasse}${line}"
    }

companion object {
        fun genSeatsList(range: Int): String {
            var stringDeAssentos = ""
            val firstClass: Int = (range * 20) / 100 //20 por cento
            val secondClass: Int = (range * 40) / 100 // 40 por cento

            for (i in 1..range + 1) {
                if (i < firstClass) {
                    stringDeAssentos += Assento('A', i).toString() + " "
                } else if (i < secondClass) {
                    stringDeAssentos += Assento('B', i).toString() + " "
                } else {
                    stringDeAssentos += Assento('C', i).toString() + " "
                }
            }

            return stringDeAssentos

        }
    }
}
