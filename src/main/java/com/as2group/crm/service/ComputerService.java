package com.as2group.crm.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.enumeration.ComputerStatus;
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

	public void changeStatus(Computer computer, ComputerStatus status) {
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
			throw new IllegalArgumentException("SerialNumber is not possible to be null");
		}
		
		computer.setStatus(ComputerStatus.PRA_USO);
		computer.setEntryDate(LocalDate.now());
		return computerRepository.save(computer);
	}

    // public Computer update(Computer computer) {

    //     if (computer.getId() == null) {
    //         throw new IllegalArgumentException("Computer ID cannot be null for update");
    //     }


    //     return computerRepository.save(computer);
    // }
	
	// Delete
	public void delete(Long id) {
		computerRepository.deleteById(id);
	}

	// Put
	public Computer edit(Long id, Computer computer) {
		Computer found = show(id);
		found.setPatrimony(computer.getPatrimony());
		found.setSn(computer.getSn());
		found.setEmployee(computer.getEmployee());
		found.setSector(computer.getSector());
		found.setModel(computer.getModel());
		found.setBrand(computer.getBrand());
		found.setSoCurrent(computer.getSoCurrent());
		found.setSoOriginal(computer.getSoOriginal());
		return computerRepository.save(found);
	}

	// Inactivate
	public void inactivate(Long id) {
		Computer computer = show(id);
		
		if(computer.getEmployee() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Computer stills with employee");
		}
		if(computer.getStatus() == ComputerStatus.INATIVO) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Computer is already inactive");
			
		}
		
		changeStatus(computer, ComputerStatus.INATIVO);
		computer.setDepartureDate(LocalDate.now());
		computerRepository.save(computer);

	}
	
	//ActivateComputer
	public void activate(Long id) {
		Computer computer = show(id);
		if(computer.getStatus() == ComputerStatus.INATIVO) {
			changeStatus(computer, ComputerStatus.PRA_USO);
			computer.setEntryDate(LocalDate.now());
			computer.setDepartureDate(null);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Computer is already active");
		}
	}

	// GetStatus
	public List<Computer> listByStatus(ComputerStatus status) {
		return computerRepository.findAllByStatus(status);
	}

	// GetEstoque
	public List<Computer> stock(){
		return computerRepository.findByStatusActivate();
	}
	

}
