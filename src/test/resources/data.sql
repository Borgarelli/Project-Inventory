insert into employee (name, email, telephone, gender, entry_date)
	values('Kauã Borgarelli', 'kaua1as74@group', '12992002060', 'Masculino', '2023-10-19'); 
    
insert into computer(id_employee, sector, model, brand, so_current, so_original, patrimony, sn, entry_date)
	values(1, 'TI', 'Inspiron 14R 5437', 'Dell', 'Ubuntu 22.04.2 LTS', 'Windows 10','NTK19188', '14719733488', '2023-10-19');

insert into components_type (description)
	values('Hd');
    
insert into components (id_computer, id_component_type, specifications, patrimony, sn)
	values(1, 1, 'green', 'NTK191253', '14719733453');
    
insert into computer_employee(id_computer, id_employee, received)
	values(1, 1, '2023-10-19 14:30:00');
    
insert into computer_components(id_computer, id_component, received)
	values(1, 1, '2023-10-19 14:00:00');