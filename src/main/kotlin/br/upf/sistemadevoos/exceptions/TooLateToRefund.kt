package br.upf.sistemadevoos.exceptions

import java.lang.RuntimeException

class TooLateToRefund (override val message: String)
: RuntimeException()