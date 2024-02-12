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


import com.as2group.crm.dto.ComputerRequest;
import com.as2group.crm.dto.ComputerResponse;
import com.as2group.crm.dto.ComputerSimpleResponse;
import com.as2group.crm.enumeration.Status;
import com.as2group.crm.mapper.ComputerMapper;


import com.as2group.crm.service.ComputerEmployeeService;
import com.as2group.crm.service.ComputerService;

@RestController
@RequestMapping("/api")
public class ComputerController {

	@Autowired
	ComputerService computerService;

	@Autowired
	ComputerEmployeeService computerEmployeeService;
		
	@Autowired
	ComputerMapper computerMapper;

	@GetMapping("/computers")
	public List<ComputerResponse> list() {
		return computerMapper.map(computerService.list());

	}

	@GetMapping("/computers/{id}")
	public ComputerResponse showId(@PathVariable("id") Long id) {
		return computerMapper.map(computerService.show(id));
	}

	@GetMapping("/computers/status/{status}")
	public List<ComputerResponse> listByStatus(@PathVariable("status") Status status) {
		return computerMapper.map(computerService.listByStatus(status));
	}
	

	@GetMapping("/computers/patrimony/{patrimony}")
	public ComputerResponse show(@PathVariable("patrimony") String patrimony) {
		return computerMapper.map(computerService.show(patrimony));
	}
	

	@PostMapping("/computers")
	@ResponseStatus(HttpStatus.CREATED)
	public ComputerResponse create(@RequestBody ComputerRequest computerRequest) {
		return computerMapper.map(computerService.create(computerMapper.map(computerRequest)));
	}

	@DeleteMapping("/computers/{id}")
	public void delete(@PathVariable("id") Long id) {
		computerService.delete(id);
	}

	@PutMapping("/computers/{id}")
	public ComputerResponse update(@PathVariable("id") Long id, @RequestBody ComputerRequest computerRequest) {
		return computerMapper.map(computerService.edit(id, computerMapper.map(computerRequest)));
	}

	@PutMapping("/computers/{id}/inactivate")
	public String inactivate(@PathVariable("id") Long id) {
		computerService.inactivate(id);
		return "Computer has been deleted successfully.";
		
	}
	
	@PutMapping("/computers/{id}/activate")
	public String activate(@PathVariable("id") Long id) {
		computerService.activate(id);
		return "Computer has been activate succesfully.";
	}

	@GetMapping("computers/stock")
	public List<ComputerSimpleResponse> stock() {
		return computerMapper.mapSimple(computerService.stock());
	}
}
