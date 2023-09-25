package com.as2group.crm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.model.ComputerEmployee;
import com.as2group.crm.model.Employee;
import com.as2group.crm.repository.ComputerEmployeeRepository;
import com.as2group.crm.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ComputerEmployeeRepository computerEmployeeRepository;

	//Constructor
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public void changeStatus(Employee employee, Employee.Status status) {
		employee.setStatus(status);
		this.employeeRepository.save(employee);
	}
	
	//List
	public List<Employee> list() {
		return employeeRepository.findAll();
	}

	//FindById
	public Employee show(Long id) {
		Optional<Employee> found = employeeRepository.findById(id);
		if (found.isPresent()) {
			return found.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
		}
	}

	//Create
	public Employee create(Employee employee) {
	    List<Employee> existingEmployees = employeeRepository.findByEmail(employee.getEmail());

	    if (!existingEmployees.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists.");
	    }

	    employee.setStatus(Employee.Status.ATIVO);
	    employee.setEntryDate(LocalDate.now());
	    return employeeRepository.save(employee);
	}

	//Delete
	public void delete(Long id) {
	    Employee employee = show(id);
	    List<ComputerEmployee> linkedComputers = computerEmployeeRepository.findByEmployee(employee);

	    List<Long> computersToUnlink = new ArrayList<>();

	    if (!linkedComputers.isEmpty()) {
	        for (ComputerEmployee computerEmployee : linkedComputers) {
	            if (computerEmployee.getReturned() == null) {
	                Long computerId = computerEmployee.getComputer().getId();
	                computersToUnlink.add(computerId);
	            }
	        }
	    }

	    if (!computersToUnlink.isEmpty()) {
	        String errorMessage = "Computers with IDs " + computersToUnlink.toString() +
	                              " are still assigned to the employee. Unlink these computers before deactivating the employee.";
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
	    }

	    changeStatus(employee, Employee.Status.INATIVO);
	    employee.setDepartureDate(LocalDate.now());
	    employeeRepository.save(employee);
	}


	
	//Lists
	public List<Employee> listActivate() {
	    return employeeRepository.findByStatusActivate();
	}

	public List<Employee> listInactivate(){
		return employeeRepository.findByStatusInactivate();
	}
	
	//Put
	public Employee edit(Employee employee, Long id) {
		Employee found = show(id);
		found.setEmail(employee.getEmail());
		found.setName(employee.getName());
		found.setSex(employee.getSex());
		found.setTelephone(employee.getTelephone());

		return employeeRepository.save(found);
	}

}
