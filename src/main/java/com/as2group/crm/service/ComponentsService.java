package com.as2group.crm.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.enumeration.ComponentsStatus;
import com.as2group.crm.model.Components;
import com.as2group.crm.repository.ComponentsRepository;

@Service
public class ComponentsService {

	@Autowired
	ComponentsRepository componentsRepository;

	// Constructor
	public ComponentsService(ComponentsRepository componentsRepository) {
		this.componentsRepository = componentsRepository;
	}

	public void changeStatus(Components Components, ComponentsStatus status) {
		Components.setStatus(status);
		this.componentsRepository.save(Components);
	}

	// GetAll
	public List<Components> list() {
		return componentsRepository.findAll();
	}

	// GetByPatrimonio
	public Components show(String patrimony) {
		Optional<Components> found = componentsRepository.findByPatrimony(patrimony);
		if (found.isPresent()) {
			return found.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Components not found");
		}
	}

	// GetById
	public Components show(Long id) {
		Optional<Components> found = componentsRepository.findById(id);
		if (found.isPresent()) {
			return found.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Components not found");
		}
	}

	// GetByStatus
	public List<Components> listByStatus(ComponentsStatus status) {
		return componentsRepository.findAllByStatus(status);
	}


	// Post
	public Components create(Components components) {
		Optional<Components> found = componentsRepository.findByPatrimony(components.getPatrimony());
		if (found.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Computer assets are already in use.");
		}
		
		if(components.getPatrimony() == null || components.getPatrimony().isEmpty()) {
			throw new IllegalArgumentException("Patrimony is not possible to be null");
		}
		
		found = componentsRepository.findBySn(components.getSn());
		if (found.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Serial Number already exists.");
		}
		
		if(components.getSn() == null || components.getSn().isEmpty()) {
			throw new IllegalArgumentException("Serial number is not possible to be null");
		}
		components.setStatus(ComponentsStatus.PRA_USO);
		return componentsRepository.save(components);
	}

	// DeleteById
	public void inactivate(Long id) {
		Components component = show(id);
		
		if(component.getComputer() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Components stills in computer");
		}
		
		if(component.getStatus() == ComponentsStatus.INATIVO) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Component is already inactive");
		}
		
		changeStatus(component, ComponentsStatus.INATIVO);
		componentsRepository.save(component);
	}

	//Activate
	public void activate(Long id) {
		Components component = show(id);
		if(component.getStatus() == ComponentsStatus.INATIVO){
			changeStatus(component, ComponentsStatus.PRA_USO);
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Component is already active");
		}
	}

	//Put
	public Components edit(Long id, Components component) {
		Components found = show(id);
		Optional<Components> AlreadyUsedPatrimony = componentsRepository.findByPatrimony(component.getPatrimony());
		Optional<Components> AlreadyUsedSn = componentsRepository.findBySn(component.getSn());

		if(AlreadyUsedPatrimony.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This patrimony is already exist");
		}

		if(component.getPatrimony() == null || component.getPatrimony().isEmpty()){
			throw new IllegalArgumentException("Patrimony is not possible to be null");
		}

		if(AlreadyUsedSn.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This serial num already exist");
		}

		if(component.getSn() == null || component.getSn().isEmpty()){
			throw new IllegalArgumentException("Serial number is not possible to be null");
		}

		found.setPatrimony(component.getPatrimony());
		found.setSn(component.getSn());
		found.setSpecifications(component.getSpecifications());
		return componentsRepository.save(found);
	}



	// GetStock
	public List<Components> stock(){
		return componentsRepository.findByStatusActivate();
	}


}
