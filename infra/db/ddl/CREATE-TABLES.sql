CREATE TABLE `employee` (
  `id_employee` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telephone` varchar(100) DEFAULT NULL,
  `sex` varchar(30) DEFAULT NULL,
  `entryDate` datetime DEFAULT NULL,
  `departureDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id_employee`)
);

CREATE TABLE `computador` (
  `id_computador` int(11) NOT NULL AUTO_INCREMENT,
  `id_funcionario` int(11) DEFAULT NULL,
  `nome` int(11) DEFAULT NULL,
  `setor` varchar(50) DEFAULT NULL,
  `modelo` varchar(50) DEFAULT NULL,
  `marca` varchar(50) DEFAULT NULL,
  `processador` varchar(50) DEFAULT NULL,
  `geracao` varchar(50) DEFAULT NULL,
  `RAM` varchar(50) DEFAULT NULL,
  `placa_de_video` varchar(50) DEFAULT NULL,
  `hd` varchar(50) DEFAULT NULL,
  `ssd` varchar(50) DEFAULT NULL,
  `so_atual` varchar(50) DEFAULT NULL,
  `so_original` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `patrimonio` varchar(50) DEFAULT NULL,
  `sn` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_computador`),
  KEY `fk_id_funcionario` (`id_funcionario`),
  CONSTRAINT `fk_id_funcionario` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id_funcionario`)
);

CREATE TABLE `computador_funcionario` (
  `id_comp_func` int(11) NOT NULL AUTO_INCREMENT,
  `id_computador` int(11) DEFAULT NULL,
  `id_funcionario` int(11) DEFAULT NULL,
  `recebido_em` datetime DEFAULT NULL,
  `devolvido_em` datetime DEFAULT NULL,
  PRIMARY KEY (`id_comp_func`),
  KEY `fk_computador` (`id_computador`),
  KEY `fk_funcionario` (`id_funcionario`),
  CONSTRAINT `fk_computador` FOREIGN KEY (`id_computador`) REFERENCES `computador` (`id_computador`),
  CONSTRAINT `fk_funcionario` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id_funcionario`)
);
