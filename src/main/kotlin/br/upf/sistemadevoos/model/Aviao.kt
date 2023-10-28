package br.upf.sistemadevoos.model

import br.upf.sistemadevoos.enums.AviaoStatus

class Aviao (
  val manufacturer : String,
  val  planeModel : String,
  val  aircraftRegistration : String,
  val  fuelTankSize : Float,
  val  avgFuelConsumption : Float,
  val  avgSpeed : Float,
  val  status : AviaoStatus
)