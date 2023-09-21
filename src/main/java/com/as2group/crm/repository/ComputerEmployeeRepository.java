package com.as2group.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.as2group.crm.model.Computer;
import com.as2group.crm.model.ComputerEmployee;
import com.as2group.crm.model.Employee;


public interface ComputerEmployeeRepository extends JpaRepository<ComputerEmployee, Long> {
	
	
	List<ComputerEmployee> findByComputerAndEmployee(Computer computer, Employee employee);
	
	List<ComputerEmployee> findByComputer(Computer computador);
	
	List<ComputerEmployee> findByEmployee(Employee employee);
	
	

}
