package com.as2group.crm.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.as2group.crm.model.Components;
import com.as2group.crm.model.Components.Status;
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

	public void changeStatus(Components Components, Status status) {
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
	public List<Components> listByStatus(Status status) {
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
		components.setStatus(Components.Status.PRA_USO);
		return componentsRepository.save(components);
	}

	// DeleteById
	public void inactivate(Long id) {
		Components component = show(id);
		
		if(component.getComputer() != null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Components stills in computer");
		}
		
		if(component.getStatus() == Components.Status.INATIVO) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Component is already inactive");
		}
		
		changeStatus(component, Components.Status.INATIVO);
		componentsRepository.save(component);
	}

	// DeleteByPatrimonio
	public void delete(String patrimony) {
		componentsRepository.deleteByPatrimony(patrimony);
	}

	// Put
//	public Components edit(Components Components, Long id, Long Patrimonio, String processor, String generation,
//			String ram, String graphicsCard, String hd, String ssd) {
//		Components found = show(id);
//		found.setProcessor(Components.getProcessor());
//		found.setGeneration(Components.getGeneration());
//		found.setRam(Components.getRam());
//		found.setGraphicsCard(Components.getGraphicsCard());
//		found.setHd(Components.getHd());
//		found.setSsd(Components.getSsd());
//		found.setPatrimony(Components.getPatrimony());
//		return componentsRepository.save(found);
//	}

//	// PutInativar
//	public void inactivate(Long id) {
//		Components components = show(id);
//		changeStatus(components, Components.Status.INATIVO);
//
//	}

	// GetStock
	public Optional<Status> stockByStatus() {
		Optional<Status> found = componentsRepository.findByStatus(Status.PRA_USO);
		if (found.isPresent()) {
			return Optional.of(found.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "estoque vazio");
		}
	}

	// GetStock
//	public Optional<ComponentType> stockByType() {
//		Optional<ComponentType> found = componentsRepository.findByType(ComponentType.valueOf(null));
//		if (found.isPresent()) {
//			return Optional.of(found.get());
//		} else {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "estoque vazio");
//		}
//	}
}
