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

import com.as2group.crm.dto.EmployeeRequest;
import com.as2group.crm.dto.EmployeeResponse;
import com.as2group.crm.mapper.EmployeeMapper;
import com.as2group.crm.model.Employee;
import com.as2group.crm.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	EmployeeMapper employeeMapper;

	//GetAll
	@GetMapping("/employees")
	public List<EmployeeResponse> list() {
	    return employeeMapper.map(employeeService.listActivate());
	}

	//GetById
	@GetMapping("/employees/{id}")
	public EmployeeResponse display(@PathVariable("id") Long id) {
		return employeeMapper.map(employeeService.show(id));
	}
	
	//GetEmployeeInactivate
	@GetMapping("/employees/inactive")
	public List<EmployeeResponse> listInactivate(){
		return employeeMapper.map(employeeService.listInactivate());
	}

	//GetAllByName
	@GetMapping("/employees/name/{name}")
	public List<EmployeeResponse> displayName(@PathVariable("name") String name) {
		return employeeMapper.map(employeeService.showName(name));
	}
	
	//GetByEmail
	@GetMapping("/employees/email/{email}")
	public EmployeeResponse displayEmail(@PathVariable("email") String email){
		return employeeMapper.map(employeeService.showEmail(email));
	}
	
	//Post
	@PostMapping("/employees")
	@ResponseStatus(HttpStatus.CREATED)
	public EmployeeResponse create(@RequestBody EmployeeRequest employeeRequest) {
		return employeeMapper.map(employeeService.create(employeeMapper.map(employeeRequest)));
		
	}

	//Delete
	@DeleteMapping("/employees/{id}")
	public String delete(@PathVariable("id") Long id) {
		employeeService.delete(id);
		return "Employee has been deleted successfully.";
	}
	
	//Activate
	@PutMapping("/employees/{id}/activate")
	public String activateEmployee(@PathVariable("id") Long id) {
	    	employeeService.activate(id);
	    	return "Employee has been activate successfully.";   
	}
	
	//Put
	@PutMapping("/employees/{id}")
	public Employee update(@PathVariable("id") Long id, @RequestBody Employee employee, String email, 
			String name, String gender, String telephone) {
		return employeeService.edit(employee, id);
	}

}
