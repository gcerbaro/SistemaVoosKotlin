CREATE TABLE `voo` (
 `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
 `origem` varchar(255) NOT NULL,
 `destino` varchar(255) NOT NULL,
 `nAssentos` INTEGER NOT NULL,
  `status` varchar(10) DEFAULT "ONTIME";
  `voo_id` bigint NOT NULL,
  `usuario_id` bigint NOT NULL,
  FOREIGN KEY
);