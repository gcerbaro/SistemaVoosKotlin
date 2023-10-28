package br.upf.sistemadevoos.exceptions

import java.lang.RuntimeException

class TooLateToRefundException (override val message: String)
: RuntimeException()