CREATE TABLE `aviao`(
`manufacturer` VARCHAR(20) NOT NULL,
`planemodel` VARCHAR(8) NOT NULL,
`aircraftregistration` VARCHAR(10) NOT NULL UNIQUE,
`fueltanksize` FLOAT,
`avgfuelconsumption` FLOAT,
`avgspeed` FLOAT,
`status` VARCHAR(12),
)