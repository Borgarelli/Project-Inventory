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

import com.as2group.crm.exceptions.ComputerNotFoundException;
import com.as2group.crm.model.Computer;
import com.as2group.crm.model.Computer.Status;
import com.as2group.crm.service.ComputerEmployeeService;
import com.as2group.crm.service.ComputerService;

@RestController
@RequestMapping("/api")
public class ComputerController {

	@Autowired
	ComputerService computerService;

	@Autowired
	ComputerEmployeeService computerEmployeeService;

	@GetMapping("/computers")
	public List<Computer> list() {
		return computerService.list();
	}

	@GetMapping("/computers/{id}")
	public Computer show(@PathVariable("id") Long id) {
		return computerService.show(id);
	}
	
	@GetMapping("/computers/status/{status}")
	public List<Computer> listByStatus(@PathVariable("status") Status status) {
	    return computerService.listByStatus(status);
	}
	
	@GetMapping("/computers/patrimony/{patrimony}")
	public Computer show(@PathVariable("patrimony") String patrimony) {
		return computerService.show(patrimony);
	}

	@PostMapping("/computers")
	@ResponseStatus(HttpStatus.CREATED)
	public Computer create(@RequestBody Computer computer) {
		return computerService.create(computer);
	}

	@DeleteMapping("/computers/{id}")
	public void delete(@PathVariable("id") Long id) {
		computerService.delete(id);
	}

	@PutMapping("/computers/{id}")
	public Computer update(@PathVariable("id") Long id, @RequestBody Computer computer, Long patrimony, String sn, String employee, String sector,
			String model, String brand, String processor, String generation, String ram, String graphicsCard, String hd,
			String ssd, String soCorrent, String soOriginal) {
		return computerService.edit(computer, id, patrimony, sn, employee, sector, model, brand, processor,
				generation, ram, graphicsCard, hd, ssd, soCorrent, soOriginal);
	}

	@PutMapping("/computers/{id}/inactivate")
	public String inactivate(@PathVariable("id") Long id) {
		try {
			computerService.inactivate(id);
			return "Computer has been deleted successfully.";
		} catch(ComputerNotFoundException c){
			
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computer not found", c);
		}
		
	}

	@GetMapping("computers/stock")
	public List<Computer> stock() {
		return computerService.stock();
	}
}
