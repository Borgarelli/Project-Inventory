-- Employee

INSERT INTO employee (name, email, telephone, gender, status, entry_date) 
VALUES ('Kauã Borgarelli', 'kauaas2@group', '12992002060', 'Masculino', 1, '2023-10-26');

INSERT INTO employee (name, email, telephone, gender, status, entry_date, departure_date) 
VALUES ('Kauã Borgarelli', 'kaua1as74@group', '12992002060', 'Masculino', 0, '2023-10-26', '2023-10-27');

INSERT INTO employee (name, email, telephone, gender, status, entry_date) 
VALUES ('Kauã Borgarelli', 'kauaas88@group', '12992002060', 'Masculino', 1, '2023-10-26');

INSERT INTO employee (name, email, telephone, gender, status, entry_date) 
VALUES ('Kauã Borgarelli', 'kauaas90@group', '12992002060', 'Masculino', 1, '2023-10-26');

-- Computer

INSERT INTO computer (sector, model, brand, so_current, so_original, patrimony, sn, entry_date, status) 
VALUES ('IT', 'Inspiron 14R 5437', 'Dell', 'Ubuntu 22.04.2 LTS', 'Windows 10', 'NTK19190', '14719733490', '2023-10-26', 1);

INSERT INTO computer (id_employee, sector, model, brand, so_current, so_original, patrimony, sn, entry_date, status) 
VALUES (1, 'IT', 'Inspiron 14R 5437', 'Dell', 'Ubuntu 22.04.2 LTS', 'Windows 10', 'NTK19188', '14719733488', '2023-10-26', 2);

INSERT INTO computer (sector, model, brand, so_current, so_original, patrimony, sn, entry_date, departure_date, status) 
VALUES ('IT', 'Inspiron 14R 5437', 'Dell', 'Ubuntu 22.04.2 LTS', 'Windows 10', 'NTK19179', '14719733479', '2023-10-26', '2023-10-27',  0);

INSERT INTO computer (id_employee, sector, model, brand, so_current, so_original, patrimony, sn, entry_date, status) 
VALUES (4, 'IT', 'Inspiron 14R 5437', 'Dell', 'Ubuntu 22.04.2 LTS', 'Windows 10', 'NTK19152', '14719733152', '2023-10-26', 2);

-- Components

insert into components (specifications, patrimony, sn, status)
	values('green', 'NTK191253', '14719733453', 1);

insert into components (id_computer, specifications, patrimony, sn, status)
	values(2, 'blue', 'NTK191265', '14719733465', 2);

insert into components (specifications, patrimony, sn, status)
	values('red', 'NTK191288', '14719733488', 0);

insert into components (id_computer, specifications, patrimony, sn, status)
	values(4, 'yellow', 'NTK191291', '14719733491', 2);


-- ComputerEmployee
insert into computer_employee(id_computer, id_employee, received)
	values(2, 1, '2023-10-19 14:30:00');

insert into computer_employee(id_computer, id_employee, received)
	values(4, 4, '2023-10-19 14:30:00');

-- ComputerComponents    
insert into computer_components(id_computer, id_component, received)
	values(2, 2, '2023-10-19 14:00:00');

insert into computer_components(id_computer, id_component, received)
	values(4, 4, '2023-10-19 14:00:00');