CREATE TABLE `usuario` (
 `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
 `nome` varchar(255) NOT NULL,
 `cidade` varchar(255) DEFAULT NULL,
);