CREATE TABLE `employee` (
  `id_employee` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telephone` varchar(100) DEFAULT NULL,
  `sex` varchar(30) DEFAULT NULL,
  `entry_date` datetime DEFAULT NULL,
  `departure_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id_employee`)
);

CREATE TABLE `computer` (
  `id_computer` int(11) NOT NULL AUTO_INCREMENT,
  `id_employee` int(11) DEFAULT NULL,
  `sector` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `processor` varchar(50) DEFAULT NULL,
  `generation` varchar(50) DEFAULT NULL,
  `ram` varchar(50) DEFAULT NULL,
  `graphics_card` varchar(50) DEFAULT NULL,
  `hd` varchar(50) DEFAULT NULL,
  `ssd` varchar(50) DEFAULT NULL,
  `so_current` varchar(50) DEFAULT NULL,
  `so_original` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `patrimony` varchar(50) DEFAULT NULL,
  `sn` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_computer`),
  KEY `fk_id_employee` (`id_employee`),
  CONSTRAINT `fk_id_employee` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id_employee`)
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
