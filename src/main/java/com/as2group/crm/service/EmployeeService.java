package com.as2group.crm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.enums.Status;
import com.as2group.crm.model.ComputerEmployee;
import com.as2group.crm.model.Employee;
import com.as2group.crm.model.Role;
import com.as2group.crm.repository.ComputerEmployeeRepository;
import com.as2group.crm.repository.EmployeeRepository;
import com.as2group.crm.repository.RoleRepository;

@Service
public class EmployeeService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ComputerEmployeeRepository computerEmployeeRepository;

	@Autowired
	private RoleRepository roleRepository;


	public void changeStatus(Employee employee, Status status) {
		employee.setStatus(status);
		this.employeeRepository.save(employee);
	}
	
	public List<Employee> list() {
		return employeeRepository.findAll();
	}

	public Employee show(Long id) {
		Optional<Employee> found = employeeRepository.findById(id);
		if (found.isPresent()) {
			return found.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
		}
	}

	public List<Employee> showName(String name) {
		List<Employee> found = employeeRepository.findByName(name);
		if (found.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");

		}
		return found;

	}
	
	public Employee showEmail(String email){
		Optional<Employee> found = employeeRepository.findByEmail(email);
		if(found.isPresent()) {
			return found.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
		}
	}
	
	public Employee create(Employee employee) {
		Optional<Employee> existingEmployees = employeeRepository.findByEmail(employee.getEmail());

		if (!existingEmployees.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already exists.");
		}

		employee.setStatus(Status.ATIVO);
		employee.setEntryDate(LocalDate.now());
		employee.setPassword(encoder.encode(employee.getPassword()));

		Role employeeRole = roleRepository.findByLevel("ROLE_EMPLOYEE");
		Set<Role> roles = new HashSet<>();
		roles.add(employeeRole);
		employee.setRoles(roles);

		return employeeRepository.save(employee);
	}

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
				else {
					throw new IllegalArgumentException("Computer already returned");
				}
	        }
	    }

	    if (!computersToUnlink.isEmpty()) {
	        String errorMessage = "Computers with IDs " + computersToUnlink.toString() +
	                              " are still assigned to the employee. Unlink these computers before deactivating the employee.";
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage);
	    }
	    
	    if(employee.getStatus() == Status.INATIVO) {
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee is already inactivate");
	    }

	    changeStatus(employee, Status.INATIVO);
	    employee.setDepartureDate(LocalDate.now());
	    employeeRepository.save(employee);
	}

	public void activate(Long id) {
		Employee employee = show(id);
		
		if(employee.getStatus() == Status.INATIVO) {
			employee.setEntryDate(LocalDate.now());
			employee.setDepartureDate(null);
			changeStatus(employee, Status.ATIVO);
				
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee is already activate");
		}
	}
	

	public List<Employee> listActivate() {
	    return employeeRepository.findByStatusActivate();
	}

	public List<Employee> listInactivate(){
		return employeeRepository.findByStatusInactivate();
	}

	public Employee edit(Employee employee, Long id) {
		Employee found = show(id);
		Optional<Employee> existingEmployee = employeeRepository.findByEmail(employee.getEmail());
		
		if(existingEmployee.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already used");
		}

		if(employee.getEmail() == null || employee.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email is not possible to be null");
		}
		
		found.setEmail(employee.getEmail());
		found.setName(employee.getName());
		found.setGender(employee.getGender());
		found.setTelephone(employee.getTelephone());

		return employeeRepository.save(found);
	}

}
