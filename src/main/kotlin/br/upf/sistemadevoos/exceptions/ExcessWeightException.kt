package br.upf.sistemadevoos.exceptions

import java.lang.RuntimeException

class ExcessWeightException(override val message: String)  : RuntimeException()