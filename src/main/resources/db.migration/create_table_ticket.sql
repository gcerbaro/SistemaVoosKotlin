CREATE TABLE `ticket` (
 `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
 `partida` datetime(6) NOT NULL,
 `embarque` datetime(6) NOT NULL,
`chegada` datetime(6) NOT NULL,
 `valor` VARCHAR(255) NOT NULL,
 `voo_id` bigint NOT NULL,
 `usuario_id` bigint NOT NULL,
 FOREIGN KEY (`voo_id`) REFERENCES `voo` (`id`),
 FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
);