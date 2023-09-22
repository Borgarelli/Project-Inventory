CREATE TABLE `employee` (
  `id_employee` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `telephone` varchar(100) DEFAULT NULL,
  `sex` varchar(30) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
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

CREATE TABLE `computer_employee` (
  `id_comp_empl` int(11) NOT NULL AUTO_INCREMENT,
  `id_computer` int(11) DEFAULT NULL,
  `id_employee` int(11) DEFAULT NULL,
  `received` datetime DEFAULT NULL,
  `returned` datetime DEFAULT NULL,
  PRIMARY KEY (`id_comp_empl`),
  KEY `fk_computer` (`id_computer`),
  KEY `fk_employee` (`id_employee`),
  CONSTRAINT `fk_computer` FOREIGN KEY (`id_computer`) REFERENCES `computer` (`id_computer`),
  CONSTRAINT `fk_employee` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id_employee`)
);
