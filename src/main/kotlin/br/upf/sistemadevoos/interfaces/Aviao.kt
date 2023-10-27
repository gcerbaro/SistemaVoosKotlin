package br.upf.sistemadevoos.interfaces

import br.upf.sistemadevoos.enums.AviaoStatus

interface Aviao {
  val manufacturer : String
  val  planeModel : String
  val  matricula : String
  val  fuelTankSize : Float
  val  avgFuelConsumption : Float
  val  avgSpeed : Float
  val  status : AviaoStatus
}