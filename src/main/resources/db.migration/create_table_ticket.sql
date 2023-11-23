CREATE TABLE `ticket` (
 `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
 `valor` VARCHAR(255) NOT NULL,
 `voo_id` bigint NOT NULL,
 `usuario_id` bigint NOT NULL,
 FOREIGN KEY (`voo_id`) REFERENCES `voo` (`id`),
 FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
);