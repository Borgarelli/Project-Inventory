package com.as2group.crm.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.exceptions.EmployeeActivationException;
import com.as2group.crm.exceptions.EmployeeNotFoundException;
import com.as2group.crm.model.Employee;
import com.as2group.crm.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	//GetAll
	@GetMapping("/employees")
	public List<Employee> list() {
	    return employeeService.listActivate();
	}

	//GetById
	@GetMapping("/employees/{id}")
	public Employee display(@PathVariable("id") Long id) {
		return employeeService.show(id);
	}
	
	//GetEmployeeInactivate
	@GetMapping("/employeess/INATIVO")
	public List<Employee> listInactivate(){
		return employeeService.listInactivate();
	}

	//GetAllByName
	@GetMapping("/employees/name/{name}")
	public List<Employee> displayName(@PathVariable("name") String name) {
		return employeeService.showName(name);
	}
	
	//GetByEmail
	@GetMapping("/employees/email/{email}")
	public List<Employee> displayEmail(@PathVariable("email") String email){
		return employeeService.showEmail(email);
	}
	
	//Post
	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody Employee employee) {
		return employeeService.create(employee);
		
	}

	//Delete
	@DeleteMapping("/employees/{id}")
	public String delete(@PathVariable("id") Long id) {
		try{
			employeeService.delete(id);
			return "Employee has been deleted successfully.";
		} catch (EmployeeNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found", e);
		}
	}
	
	//Activate
	@PutMapping("/employees/{id}/activate")
	public String activateEmployee(@PathVariable("id") Long id) {
	    try {
	    	employeeService.activate(id);
	    	return "Employee has been activate successfully.";
	    } catch (EmployeeNotFoundException e) {
	    	throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found", e);
	    	
	    } catch(EmployeeActivationException e) {	
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
	    }
	    
	}
	
	//Put
	@PutMapping("/employees/{id}")
	public Employee update(@PathVariable("id") Long id, @RequestBody Employee employee, String email, 
			String name, String sex, String telephone) {
		return employeeService.edit(employee, id);
	}

}
