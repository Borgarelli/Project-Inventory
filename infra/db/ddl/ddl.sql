drop schema if exists spring;

drop user if exists 'user'@'localhost';

create schema spring;

create user 'user'@'localhost' identified by 'inventory-pass';

grant select, insert, delete, update on spring.* to user@'localhost';

CREATE TABLE `employee` (
  `id_employee` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL UNIQUE,
  `password` varchar(100) NOT NULL,
  `telephone` varchar(100) NOT NULL,
  `gender` varchar(30) NOT NULL,
  `status` int(11) NOT NULL,
  `entry_date` datetime NOT NULL,
  `departure_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id_employee`)
);

CREATE TABLE `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `level` varchar(100) NOT NULL,
  PRIMARY KEY (`id_role`)
);

CREATE TABLE `employee_role` (
  `id_employee_role` int(11) NOT NULL AUTO_INCREMENT,
  `id_employee` int(11) NOT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_employee_role`),
  CONSTRAINT `fk_id_employee_role` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id_employee`),
  CONSTRAINT `fk_id_role_employee` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`)
);

CREATE TABLE `computer` (
  `id_computer` int(11) NOT NULL AUTO_INCREMENT,
  `id_employee` int(11) NOT NULL,
  `sector` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `so_current` varchar(50) NOT NULL,
  `so_original` varchar(50) NOT NULL,
  `status` int(11) NOT NULL,
  `patrimony` varchar(50) NOT NULL UNIQUE,
  `sn` varchar(50) NOT NULL UNIQUE,
  `entry_date` datetime NOT NULL,
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
  `specifications` varchar(100) NOT NULL,
  `status` int(11) NOT NULL,
  `patrimony` varchar(50) NOT NULL UNIQUE,
  `sn` varchar(50) NOT NULL UNIQUE,
  PRIMARY KEY (`id_component`),
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
  KEY `fk_cc_component` (`id_component`),
  CONSTRAINT `fk_cc_computerr` FOREIGN KEY (`id_computer`) REFERENCES `computer` (`id_computer`),
  CONSTRAINT `fk_cc_component` FOREIGN KEY (`id_component`) REFERENCES `components` (`id_component`)
);