package com.as2group.crm.controller;

import java.util.List;
import java.util.Optional;

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

import com.as2group.crm.model.Computer;
import com.as2group.crm.model.Computer.Status;
import com.as2group.crm.repository.ComputerRepository;
import com.as2group.crm.service.ComputadorFuncionarioService;
import com.as2group.crm.service.ComputerService;

@RestController
@RequestMapping("/api")
public class ComputerController {

	@Autowired
	ComputerRepository computerRepository;

	@Autowired
	ComputerService computerService;

	@Autowired
	ComputadorFuncionarioService computadorFuncionarioService;

	@GetMapping("/computers")
	public List<Computer> list() {
		return computerService.list();
	}

	@GetMapping("/computers/status/{status}")
	public List<Computer> listByStatus(@PathVariable("status") Status status) {
	    return computerService.listByStatus(status);
	}
	
	@GetMapping("/computers/{id}")
	public Computer show(@PathVariable("id") Long id) {
		return computerService.show(id);
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

//	@PutMapping("/computers/{id}/inactivate")
//	public void inactivate(@PathVariable("id") Long id) {
//		computadorFuncionarioService.unlink(id, id);
//		computerService.inactivate(id);
//	}

	@GetMapping("computers/stock")
	public Optional<Status> stock() {
		return computerService.stock();
	}
}
