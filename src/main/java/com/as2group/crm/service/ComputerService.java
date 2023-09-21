package com.as2group.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.model.Computer;
import com.as2group.crm.model.Computer.Status;
import com.as2group.crm.repository.ComputerRepository;

@Service
public class ComputerService {

	ComputerRepository computerRepository;

	// Constructor
	public ComputerService(ComputerRepository computadorRepository) {
		this.computerRepository = computadorRepository;
	}

	public void changeStatus(Computer computer, Computer.Status status) {
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
	public Computer show(String patrimonio) {
		Optional<Computer> found = computerRepository.findByPatrimony(patrimonio);
		if (found.isPresent()) {
			return found.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Computer not found");
		}
	}

	// Post
	public Computer create(Computer computador) {
		Optional<Computer> found = computerRepository.findByPatrimony(computador.getPatrimony());
		if (found.isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patrimonio do computador já está em uso.");

		computador.setStatus(Computer.Status.PRA_USO);
		return computerRepository.save(computador);
	}

	// Delete
	public void delete(Long id) {
		computerRepository.deleteById(id);
	}

	// Put
	public Computer edit(Computer computer, Long id, Long patrimony, String sn, String employee, String sector,
			String model, String brand, String processor, String generation, String ram, String graphicsCard, String hd,
			String ssd, String soCorrent, String soOriginal) {
		Computer found = show(id);
		found.setPatrimony(computer.getPatrimony());
		found.setSn(computer.getSn());
		found.setEmployee(computer.getEmployee());
		found.setSector(computer.getSector());
		found.setModel(computer.getModel());
		found.setBrand(computer.getBrand());
		found.setProcessor(computer.getProcessor());
		found.setGeneration(computer.getGeneration());
		found.setRam(computer.getRam());
		found.setGraphicsCard(computer.getGraphicsCard());
		found.setHd(computer.getHd());
		found.setSsd(computer.getSsd());
		found.setSoCorrent(computer.getSoCorrent());
		found.setSoOriginal(computer.getSoOriginal());

		return computerRepository.save(found);
	}

	// PutInativar
	public void inactivate(Long id) {
		Computer computer = show(id);
		changeStatus(computer, Computer.Status.INATIVO);

	}

	// GetStatus
	public List<Computer> listByStatus(Status status) {
	    return computerRepository.findAllByStatus(status);
	}

	
	//GetEstoque
	public Optional<Status> stock() {
		Optional<Status> found = computerRepository.findByStatus(Status.PRA_USO);
		if (found.isPresent()) {
			return Optional.of(found.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "estoque vazio");
		}
	
	}

}
