package br.upf.sistemadevoos.exceptions

import java.lang.RuntimeException

class NotFoundException(override val message: String)
    : RuntimeException()