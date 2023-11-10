CREATE TABLE `usuario` (
 `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
 `title` varchar(10) DEFAULT "MR",
 `nome` varchar(50) NOT NULL,
 `cidade` varchar(50) DEFAULT NULL,
 `senha` varchar(255) NOT NULL,
 `email` varchar(50) NOT NULL UNIQUE,
 `role` varchar(10) DEFAULT "USER";
);