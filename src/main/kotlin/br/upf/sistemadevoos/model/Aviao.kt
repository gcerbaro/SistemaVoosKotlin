package br.upf.sistemadevoos.model

import br.upf.sistemadevoos.enums.AviaoStatus
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

open class Aviao (
  val manufacturer : String,
  val  planeModel : String,
  val  aircraftRegistration : String,
  val  fuelTankSize : Float,
  val  avgFuelConsumption : Float,
  val  avgSpeed : Float,
  @Enumerated(value = EnumType.STRING)
  val  status : AviaoStatus
)