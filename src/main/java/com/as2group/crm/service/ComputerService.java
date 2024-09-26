package com.as2group.crm.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.enums.Status;
import com.as2group.crm.model.Computer;
import com.as2group.crm.repository.ComputerEmployeeRepository;
import com.as2group.crm.repository.ComputerRepository;


@Service
public class ComputerService {

	@Autowired
	ComputerRepository computerRepository;
	
	@Autowired
	ComputerEmployeeRepository computerEmployeeRepository;
	
	// Constructor
	public ComputerService(ComputerRepository computerRepository) {
		this.computerRepository = computerRepository;
	}

	public void changeStatus(Computer computer, Status status) {
		computer.setStatus(status);
		this.computerRepository.save(computer);
	}

	// GetAll
	public List<Computer> list() {
		return computerRepository.findAll();
	}

	// GetById
	public Computer show(Long id) {
		Optional<Computer> found = computerRepository.findById(id);
		if (found.isPresent()) {
			return found.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computer not found");
		}
	}

	// GetByPatrimonio
	public Computer show(String patrimony) {
		Optional<Computer> found = computerRepository.findByPatrimony(patrimony);
		if (found.isPresent()) {
			return found.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computer not found");
		}
	}

	// Post
	public Computer create(Computer computer) {
		Optional<Computer> found = computerRepository.findByPatrimony(computer.getPatrimony());
		if (found.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Computer assets are already in use.");
		}
		
		if(computer.getPatrimony() == null || computer.getPatrimony().isEmpty()) {
			throw new IllegalArgumentException("Patrimony is not possible to be null");
		}
		
		found = computerRepository.findBySn(computer.getSn());
		if (found.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Serial Number already exists.");
		}
		
		if(computer.getSn() == null || computer.getSn().isEmpty()) {
			throw new IllegalArgumentException("Serial number is not possible to be null");
		}
		
		computer.setStatus(Status.PRA_USO);
		computer.setEntryDate(LocalDate.now());
		return computerRepository.save(computer);
	}
	
	// Delete
	public void delete(Long id) {
		show(id);
		computerRepository.deleteById(id);
		
	}

	// Put
	public Computer edit(Long id, Computer computer) {
		Computer found = show(id);
		Optional<Computer> AlreadyUsedPatrimony = computerRepository.findByPatrimony(computer.getPatrimony());
		Optional<Computer> AlreadyUsedSn = computerRepository.findBySn(computer.getSn());

		if(AlreadyUsedPatrimony.isPresent()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patrimony already in use");
		}

		if(computer.getPatrimony() == null || computer.getPatrimony().isEmpty()){
			throw new IllegalArgumentException("Patrimony is not possible to be null");
		}

		if(AlreadyUsedSn.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Serial number Already in use");
		}

		if(computer.getSn() == null || computer.getSn().isEmpty()) {
			throw new IllegalArgumentException("Serial number canot be null");
		}

		found.setPatrimony(computer.getPatrimony());
		found.setSn(computer.getSn());
		found.setSector(computer.getSector());
		found.setModel(computer.getModel());
		found.setBrand(computer.getBrand());
		found.setSoCurrent(computer.getSoCurrent());
		found.setSoOriginal(computer.getSoOriginal());
		return computerRepository.save(found);
	}

	// PutInativar
	public void inactivate(Long id) {
		Computer computer = show(id);
		
		if(computer.getEmployee() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Computer stills with employee");
		}
		if(computer.getStatus() == Status.INATIVO) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Computer is already inactive");
			
		}
		
		changeStatus(computer, Status.INATIVO);
		computer.setDepartureDate(LocalDate.now());
		computerRepository.save(computer);

	}
	
	public void activate(Long id) {
		Computer computer = show(id);
		if(computer.getStatus() == Status.INATIVO) {
			changeStatus(computer, Status.PRA_USO);
			computer.setEntryDate(LocalDate.now());
			computer.setDepartureDate(null);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Computer is already active");
		}
	}

	// GetStatus
	public List<Computer> listByStatus(Status status) {
		return computerRepository.findAllByStatus(status);
	}

	// GetEstoque
	public List<Computer> stock(){
		return computerRepository.findByStatusActivate();
	}
	
}
