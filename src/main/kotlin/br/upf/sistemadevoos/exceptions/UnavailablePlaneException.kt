package br.upf.sistemadevoos.exceptions

import java.lang.RuntimeException

class UnavailablePlaneException (override val message: String) : RuntimeException()