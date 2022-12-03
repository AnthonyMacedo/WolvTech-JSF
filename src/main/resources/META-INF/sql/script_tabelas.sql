CREATE TABLE `endereco` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cep` varchar(10) DEFAULT NULL,
  `logradouro` varchar(80) DEFAULT NULL,
  `bairro` varchar(80) DEFAULT NULL,
  `complemento` varchar(80) DEFAULT NULL,
  `municipio` varchar(80) DEFAULT NULL,
  `numero` varchar(6) DEFAULT NULL,
  `uf` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `cliente` (
 `id` bigint NOT NULL AUTO_INCREMENT,
 `nome` varchar(80) DEFAULT NULL,
 `cpf` varchar(15) DEFAULT NULL, 
 `telefone` varchar(16) DEFAULT NULL,
 `celular` varchar(16) DEFAULT NULL,
 `email` varchar(60) DEFAULT NULL,
 `sexo` varchar(1) DEFAULT NULL,
 `data_nascimento` date DEFAULT NULL,
 `endereco_id` bigint DEFAULT NULL,
 PRIMARY KEY (`id`),
  KEY (`endereco_id`),
  CONSTRAINT FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `funcionario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) DEFAULT NULL,
  `cpf` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telefone` varchar(16) DEFAULT NULL,
  `celular` varchar(16) DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  `usuario` varchar(10) DEFAULT NULL,
  `senha` varchar(10) DEFAULT NULL,
  `endereco_id` bigint DEFAULT NULL,
   PRIMARY KEY (`id`),
  KEY (`endereco_id`),
  CONSTRAINT FOREIGN KEY (`endereco_id`) REFERENCES `endereco` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `apartamento` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `num_quarto` varchar(3) NOT NULL,
  `qtd_ocupantes` int DEFAULT NULL,
  `status_apartamento` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY  (`num_quarto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `reservas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cliente_id` bigint DEFAULT NULL,
  `apartamento_id` bigint DEFAULT NULL,
  `data_inicial` date DEFAULT NULL,
  `data_final` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY (`cliente_id`),
  KEY (`apartamento_id`),
  CONSTRAINT FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT FOREIGN KEY (`apartamento_id`) REFERENCES `apartamento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;