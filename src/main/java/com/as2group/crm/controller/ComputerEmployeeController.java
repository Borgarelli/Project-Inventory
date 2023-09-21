package com.as2group.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.as2group.crm.model.ComputerEmployee;
import com.as2group.crm.service.ComputerEmployeeService;

@RestController
@RequestMapping("/api")
public class ComputerEmployeeController {

	@Autowired
	private ComputerEmployeeService computerEmployeeService;

	@PostMapping("/computers/{computerId}/employees/{employeeId}")
	@ResponseStatus(HttpStatus.CREATED)
	public ComputerEmployee create(@PathVariable("computerId") Long computerId,
			@PathVariable("employeeId") Long employeeId) {
		return computerEmployeeService.link(computerId, employeeId);
	}
 
	@DeleteMapping("/computers/{computerId}/employees/{employeeId}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("computerId") Long computerId,
			@PathVariable("employeeId") Long employeeId) {
		computerEmployeeService.unlink(computerId, employeeId);
	}
	
	@GetMapping("/computers/{computerId}/historic")
	public List<ComputerEmployee> historicComputer(@PathVariable("computerId") Long computerId) { 
		return computerEmployeeService.historicComputer(computerId); 
	}
	
	@GetMapping("/employees/{employeeId}/historic")
	public List<ComputerEmployee> historicEmployee(@PathVariable("employeeId") Long employeeId) { 
		return computerEmployeeService.historicEmployee(employeeId); 
	}

}
