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

//import ch.qos.logback.core.subst.Token.Type;

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

//	// GetByType
//	public List<Components> listByType(ComponentType componentType) {
//		return componentsRepository.findAllByType(componentType);
//	}

	// Post
	public Components create(Components components) {
		Optional<Components> found = componentsRepository.findByPatrimony(components.getPatrimony());
		if (found.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Components assets are already in use.");
		}
		found = componentsRepository.findByPatrimony(components.getPatrimony());
		if (found.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Serial Number already exists.");
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

	// DeleteByPatrimonio
	public void delete(String patrimony) {
		componentsRepository.deleteByPatrimony(patrimony);
	}

	//stock
	public List<Components>stock() {
		return componentsRepository.findByStatusActive();
	}

}
