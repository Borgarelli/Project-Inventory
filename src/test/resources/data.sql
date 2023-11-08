-- Inserir registros de Employee
INSERT INTO employee (name, email, telephone, gender, status, entry_date) 
VALUES ('Kauã Borgarelli', 'kauaas2@group', '12992002060', 'Masculino', 1, '2023-10-26');

INSERT INTO employee (name, email, telephone, gender, status, entry_date, departure_date) 
VALUES ('Kauã Borgarelli', 'kaua1as74@group', '12992002060', 'Masculino', 0, '2023-10-26', '2023-10-27');

INSERT INTO employee (name, email, telephone, gender, status, entry_date) 
VALUES ('Kauã Borgarelli', 'kauaas88@group', '12992002060', 'Masculino', 1, '2023-10-26');

-- Inserir registros de Computer
INSERT INTO computer (sector, model, brand, so_current, so_original, patrimony, sn, entry_date, status) 
VALUES ('IT', 'Inspiron 14R 5437', 'Dell', 'Ubuntu 22.04.2 LTS', 'Windows 10', 'NTK19190', '14719733490', '2023-10-26', 1);

INSERT INTO computer (id_employee, sector, model, brand, so_current, so_original, patrimony, sn, entry_date, status) 
VALUES (2, 'IT', 'Inspiron 14R 5437', 'Dell', 'Ubuntu 22.04.2 LTS', 'Windows 10', 'NTK19188', '14719733488', '2023-10-26', 2);

INSERT INTO computer (sector, model, brand, so_current, so_original, patrimony, sn, entry_date, departure_date, status) 
VALUES ('IT', 'Inspiron 14R 5437', 'Dell', 'Ubuntu 22.04.2 LTS', 'Windows 10', 'NTK19179', '14719733479', '2023-10-26', '2023-10-27',  0);

insert into components_type (description)
	values('Hd');
    
insert into components (id_computer, id_component_type, specifications, patrimony, sn)
	values(1, 1, 'green', 'NTK191253', '14719733453');
    
insert into computer_employee(id_computer, id_employee, received)
	values(2, 1, '2023-10-19 14:30:00');
    
insert into computer_components(id_computer, id_component, received)
	values(2, 1, '2023-10-19 14:00:00');