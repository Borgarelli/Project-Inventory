drop table if exists computer_components;
drop table if exists components;
drop table if exists components_type;
drop table if exists computer_employee;
drop table if exists computer;
drop table if exists employee;


CREATE TABLE employee (
  id_employee bigint NOT NULL auto_increment,
  name varchar(100) DEFAULT NULL,
  email varchar(100) DEFAULT NULL,
  telephone varchar(100) DEFAULT NULL,
  gender varchar(30) DEFAULT NULL,
  status tinyint DEFAULT NULL,
  entry_date date DEFAULT NULL,
  departure_date date DEFAULT NULL,
  PRIMARY KEY (id_employee),
  UNIQUE(email)
);


CREATE TABLE computer (
  id_computer bigint NOT NULL auto_increment,
  id_employee bigint DEFAULT NULL,
  sector varchar(50) DEFAULT NULL,
  model varchar(50) DEFAULT NULL,
  brand varchar(50) DEFAULT NULL,
  so_current varchar(50) DEFAULT NULL,
  so_original varchar(50) DEFAULT NULL,
  status tinyint DEFAULT NULL,
  patrimony varchar(50) DEFAULT NULL,
  sn varchar(50) DEFAULT NULL,
  entry_date date DEFAULT NULL,
  departure_date date DEFAULT NULL,
  modification_date date DEFAULT NULL,
  PRIMARY KEY (id_computer), 
  CONSTRAINT fk_id_employee FOREIGN KEY (id_employee) REFERENCES employee (id_employee) ON DELETE RESTRICT ON UPDATE CASCADE,
  UNIQUE(patrimony),
  UNIQUE(sn)
);


CREATE TABLE components_type (
  id_component_type bigint NOT NULL auto_increment,
  description varchar(255) NOT NULL,
  PRIMARY KEY (id_component_type)
);

CREATE TABLE components (
  id_component bigint NOT NULL AUTO_INCREMENT,
  id_computer bigint,
  id_component_type bigint,
  specifications VARCHAR(100),
  status tinyint,
  patrimony VARCHAR(50),
  sn VARCHAR(50),
  PRIMARY KEY (id_component),
  FOREIGN KEY (id_computer) REFERENCES computer (id_computer),
  FOREIGN KEY (id_component_type) REFERENCES components_type (id_component_type),
  UNIQUE (patrimony),
  UNIQUE (sn)
);



CREATE TABLE computer_employee (
  id_comp_empl bigint NOT NULL AUTO_INCREMENT,
  id_computer bigint,
  id_employee bigint,
  received datetime,
  returned datetime,
  PRIMARY KEY (id_comp_empl),
  FOREIGN KEY (id_computer) REFERENCES computer (id_computer) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (id_employee) REFERENCES employee (id_employee) ON DELETE RESTRICT ON UPDATE CASCADE
);


CREATE TABLE computer_components (
  id_comp_compo bigint NOT NULL AUTO_INCREMENT,
  id_computer bigint,
  id_component bigint,
  received datetime,
  returned datetime,
  PRIMARY KEY (id_comp_compo),
  FOREIGN KEY (id_computer) REFERENCES computer (id_computer) ON DELETE RESTRICT ON UPDATE CASCADE,
  FOREIGN KEY (id_component) REFERENCES components (id_component) ON DELETE RESTRICT ON UPDATE CASCADE
);