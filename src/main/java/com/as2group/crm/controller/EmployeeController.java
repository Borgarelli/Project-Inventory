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
import com.as2group.crm.model.Employee;
import com.as2group.crm.repository.EmployeeRepository;
import com.as2group.crm.service.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employees")
	public List<Employee> list() {
	    return employeeService.listActivate();
	}

	
	@GetMapping("/employees/INATIVO")
	public List<Employee> listInactivate(){
		return employeeService.listInactivate();
	}
	


	@GetMapping("/employee/{id}")

	public Employee display(@PathVariable("id") Long id) {
		return employeeService.show(id);
	}

	@PostMapping("/employee")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody Employee employee) {
		return employeeService.create(employee);
	}

	@DeleteMapping("/employee/{id}")
	public void delete(@PathVariable("id") Long id) {
		employeeService.delete(id);
	}

	@PutMapping("/employee/{id}")
	public Employee update(@PathVariable("id") Long id, @RequestBody Employee employee, String email, 
			String name, String sex, String telephone) {
		return employeeService.edit(employee, id);
	}

}
