package com.as2group.crm.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.as2group.crm.model.Computer;
import com.as2group.crm.model.ComputerEmployee;
import com.as2group.crm.model.Employee;
import com.as2group.crm.repository.ComputerEmployeeRepository;

@Service
public class ComputerEmployeeService {

	@Autowired
	private ComputerEmployeeRepository computerEmployeeRepository;
	
	@Autowired
	private ComputerService computerService;
	@Autowired
	private EmployeeService employeeService;

	public ComputerEmployee link(Long computerId, Long employeeId) {
		Computer computer = computerService.show(computerId);
		Employee employee = employeeService.show(employeeId);

		ComputerEmployee relationship  = new ComputerEmployee();

		relationship.setComputer(computer);
		relationship.setEmployee(employee);
		relationship.setReceived(LocalDateTime.now());
		computer.setEmployee(employee);
		computerService.changeStatus(computer, Computer.Status.EM_USO);

		return computerEmployeeRepository.save(relationship);
	}

	
	public void unlink(Long computerId, Long employeeId) {
	    Computer computer = computerService.show(computerId);
	    Employee employee = employeeService.show(employeeId);

	    List<ComputerEmployee> link = computerEmployeeRepository
	            .findByComputerAndEmployee(computer, employee);
	    
	    for (ComputerEmployee computerEmployee : link) {
	        if (computerEmployee.getReturned() == null) {
	        	computer.setEmployee(null);
	        	computerEmployee.setReturned(LocalDateTime.now());
	            computerEmployeeRepository.save(computerEmployee); 
	            computerService.changeStatus(computer, Computer.Status.PRA_USO);
	        }
	    }
	}


	public void unlink(Long computerId) {
		Computer computer = computerService.show(computerId);

		List<ComputerEmployee> link = computerEmployeeRepository.findByComputer(computer);
		for (ComputerEmployee computerEmployee : link) {
			computerEmployee.setReturned(LocalDateTime.now());
			computerService.changeStatus(computer, Computer.Status.PRA_USO);
			computerEmployeeRepository.save(computerEmployee);
		}

	}

	public List<ComputerEmployee> historicComputer(Long computerId) {
		Computer computer = computerService.show(computerId);
		return computerEmployeeRepository.findByComputer(computer);
	}

	public List<ComputerEmployee> historicEmployee(Long employeeId){
		Employee employee = employeeService.show(employeeId);
		return computerEmployeeRepository.findByEmployee(employee);
	}

}