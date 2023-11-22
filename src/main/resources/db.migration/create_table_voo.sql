CREATE TABLE `voo` (
 `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
 `origem` varchar(255) NOT NULL,
 `destino` varchar(255) NOT NULL,
 `partida` datetime(6) NOT NULL,
 `embarque` datetime(6) NOT NULL,
 `chegada` datetime(6) NOT NULL,
 `nAssentos` INTEGER NOT NULL,
 `assentosDisp` VARCHAR(1800)
);