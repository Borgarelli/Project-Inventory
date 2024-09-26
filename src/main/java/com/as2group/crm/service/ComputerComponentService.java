package com.as2group.crm.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.enumeration.Status;
import com.as2group.crm.model.Components;
import com.as2group.crm.model.Computer;
import com.as2group.crm.model.ComputerComponent;
import com.as2group.crm.repository.ComputerComponentRepository;

@Service
public class ComputerComponentService {

	@Autowired
	private ComputerComponentRepository computerComponentRepository;
	
	@Autowired
	private ComputerService computerService;
	
	@Autowired
	private ComponentsService componentService;
	
	//link
    public ComputerComponent link(Long computerId, Long componentId) {
        Computer computer = computerService.show(computerId);
        Components component = componentService.show(componentId);


        if (computer.getStatus() == Status.INATIVO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Computer is not available");
        }

        if (component.getStatus() != Status.PRA_USO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Component is not available");
        }

        ComputerComponent relationship = new ComputerComponent();
        relationship.setComponent(component);
        relationship.setComputer(computer);
        relationship.setReceived(LocalDateTime.now());
        component.setComputer(computer);
        computer.getComputerWithComponents().add(component);
        computer.setModificationDate(LocalDate.now());
        componentService.changeStatus(component, Status.EM_USO);

        return computerComponentRepository.save(relationship);
    }
    
    //unlink
    public void unlink(Long computerId, Long componenId) {
    	Computer computer = computerService.show(computerId);
    	Components component = componentService.show(componenId);
    	
    	List<ComputerComponent> link = computerComponentRepository.findByComputerAndComponent(computer, component);
    	
		if (link.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Component is not linked to this computer");
		}
		    	
    	for(ComputerComponent computerComponent : link) {
    		if(computerComponent.getReturned() == null) {
    			computer.getComputerWithComponents().remove(component);
    			computer.setModificationDate(LocalDate.now());
    			component.setComputer(null);
    			computerComponent.setReturned(LocalDateTime.now());
    			computerComponentRepository.save(computerComponent);
    			componentService.changeStatus(component, Status.PRA_USO);
    		}
			else {
				throw new IllegalArgumentException("Component already returned");
			}
    	}
    }

	public ComputerComponent findById(Long id){
		Optional<ComputerComponent> found = computerComponentRepository.findById(id);
		if(found.isPresent()){
			return found.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "register not found");
		}
	}
    
    //historic
    public List<ComputerComponent> historicComponent(Long id_component){
    	Components component = componentService.show(id_component);
    	return computerComponentRepository.findByComponent(component);
    }

}