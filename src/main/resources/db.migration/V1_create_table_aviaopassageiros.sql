CREATE TABLE `aviaopassageiros` (
`id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
`manufacturer` VARCHAR(20) NOT NULL,
`planemodel` VARCHAR(8) NOT NULL,
`aircraftregistration` VARCHAR(10) NOT NULL UNIQUE,
`fueltanksize` FLOAT,
`avgfuelconsumption` FLOAT,
`avgspeed` FLOAT,
`status` VARCHAR(12),
`passengers` INT,
`linhas` INT,
`colunas` INT,
`seatlist` VARCHAR(1800);
)