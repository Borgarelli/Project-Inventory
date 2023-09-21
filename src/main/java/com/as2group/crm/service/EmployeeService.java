package com.as2group.crm.service;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.model.ComputadorFuncionario;
import com.as2group.crm.model.Employee;
import com.as2group.crm.repository.ComputadorFuncionarioRepository;
import com.as2group.crm.repository.EmployeeRepository;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ComputadorFuncionarioRepository computadorFuncionarioRepository;

	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> list() {
		return employeeRepository.findAll();
	}

	public Employee display(Long id) {
		Optional<Employee> found = employeeRepository.findById(id);
		if (found.isPresent()) {
			return found.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
		}
	}

	public Employee create(Employee employee) {
		employee.setEntryDate(LocalDate.now());
		
		return employeeRepository.save(employee);
	}

	public void delete(Long id) {
		Employee employee = display(id);
		List<ComputadorFuncionario> relatedRecords = computadorFuncionarioRepository.findByEmployee(employee);
		computadorFuncionarioRepository.deleteAll(relatedRecords);
		employeeRepository.deleteById(id);
	}
	

	
	public Employee edit(Employee employee, Long id) {
		Employee found = display(id);
		found.setEmail(employee.getEmail());
		found.setName(employee.getName());
		found.setSex(employee.getSex());
		found.setTelephone(employee.getTelephone());

		return employeeRepository.save(found);
	}

}
