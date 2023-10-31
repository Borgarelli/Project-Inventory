package com.as2group.crm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.enumeration.EmployeeStatus;
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

	public void changeStatus(Employee employee, EmployeeStatus status) {
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

	//FindByName
	public List<Employee> showName(String name) {
		return employeeRepository.findByName(name) ;
	}
	
	//FindByEmail
	public Employee showEmail(String email){
		Optional<Employee> found = employeeRepository.findByEmail(email);
		if(found.isPresent()) {
			return found.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
		}
	}
	
	//Create
	public Employee create(Employee employee) {
	    Optional<Employee> existingEmployees = employeeRepository.findByEmail(employee.getEmail());

	    if (!existingEmployees.isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists.");
	    }

	    employee.setStatus(EmployeeStatus.ATIVO);
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
	    
	    if(employee.getStatus() == EmployeeStatus.INATIVO) {
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee is already inactivate");
	    }

	    changeStatus(employee, EmployeeStatus.INATIVO);
	    employee.setDepartureDate(LocalDate.now());
	    employeeRepository.save(employee);
	}

	//Activate
	public void activate(Long id) {
		Employee employee = show(id);
		
		if(employee.getStatus() == EmployeeStatus.INATIVO) {
			employee.setEntryDate(LocalDate.now());
			employee.setDepartureDate(null);
			changeStatus(employee, EmployeeStatus.ATIVO);
				
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee is already activate");
		}
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
		Optional<Employee> existingEmployee = employeeRepository.findByEmail(employee.getEmail());
		
		if(!existingEmployee.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already used");
		}
		found.setEmail(employee.getEmail());
		found.setName(employee.getName());
		found.setGender(employee.getGender());
		found.setTelephone(employee.getTelephone());

		return employeeRepository.save(found);
	}

}
