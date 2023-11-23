package br.upf.sistemadevoos.exceptions

import java.lang.RuntimeException

class InvalidParametersException(override val message: String) : RuntimeException()
