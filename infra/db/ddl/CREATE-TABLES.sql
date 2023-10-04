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
  `so_current` varchar(50) DEFAULT NULL,
  `so_original` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `patrimony` varchar(50) DEFAULT NULL,
  `sn` varchar(50) DEFAULT NULL,
  `entry_date` datetime DEFAULT NULL,
  `departure_date` datetime DEFAULT NULL,
  `modification_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id_computer`),
  KEY `fk_id_employee` (`id_employee`),
  CONSTRAINT `fk_id_employee` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id_employee`)
);

CREATE TABLE `components_type` (
  `id_component_type` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(100),
  PRIMARY KEY (`id_component_type`)
);

CREATE TABLE `components` (
  `id_component` int(11) NOT NULL AUTO_INCREMENT,
  `id_computer` int(11) DEFAULT NULL,
  `id_component_type` int(11) DEFAULT NULL,
  `specifications` varchar(100),
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_components`),
  KEY `fk_computer` (`id_computer`),
  KEY `fk_component_type` (`id_component_type`),
  CONSTRAINT `fk_id_computer` FOREIGN KEY (`id_computer`) REFERENCES `computer` (`id_computer`),
  CONSTRAINT `fk_id_component_type` FOREIGN KEY (`id_component_type`) REFERENCES `components_type` (`id_component_type`)
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

CREATE TABLE `computer_components` (
  `id_comp_compo` int(11) NOT NULL AUTO_INCREMENT,
  `id_computer` int(11) DEFAULT NULL,
  `id_component` int(11) DEFAULT NULL,
  `received` datetime DEFAULT NULL,
  `returned` datetime DEFAULT NULL,
  PRIMARY KEY (`id_comp_compo`),
  KEY `fk_cc_computer` (`id_computer`),
  KEY `fk_cc_component` (`id_employee`),
  CONSTRAINT `fk_cc_computerr` FOREIGN KEY (`id_computer`) REFERENCES `computer` (`id_computer`),
  CONSTRAINT `fk_cc_component` FOREIGN KEY (`id_component`) REFERENCES `employee` (`id_component`)
);

