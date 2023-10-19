package com.as2group.crm.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.enumeration.ComponentsStatus;
import com.as2group.crm.enumeration.ComputerStatus;
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


        if (computer.getStatus() == ComputerStatus.INATIVO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Computer is not available");
        }

        if (component.getStatus() != ComponentsStatus.PRA_USO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Component is not available");
        }

        ComputerComponent relationship = new ComputerComponent();
        relationship.setComponent(component);
        relationship.setComputer(computer);
        relationship.setReceived(LocalDateTime.now());
        component.setComputer(computer);
        computer.getComputerComponents().add(component);
        computer.setModificationDate(LocalDate.now());
        componentService.changeStatus(component, ComponentsStatus.EM_USO);

        return computerComponentRepository.save(relationship);
    }
    
    //unlink
    public void unlink(Long computerId, Long componenId) {
    	Computer computer = computerService.show(computerId);
    	Components component = componentService.show(componenId);
    	
    	List<ComputerComponent> link = computerComponentRepository.findByComputerAndComponent(computer, component);
    	
    	if (component.getStatus() == ComponentsStatus.INATIVO) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Component is inactive");
    	}
    	
    	for(ComputerComponent computerComponent : link) {
    		if(computerComponent.getReturned() == null) {
    			computer.getComputerComponents().remove(component);
    			computer.setModificationDate(LocalDate.now());
    			component.setComputer(null);
    			computerComponent.setReturned(LocalDateTime.now());
    			computerComponentRepository.save(computerComponent);
    			componentService.changeStatus(component, ComponentsStatus.PRA_USO);
    		}
    	}
    }

	//HistoricComponent
	public List<ComputerComponent> historicComponent(Long Idcomponent) {
		Components component = componentService.show(Idcomponent);
		return computerComponentRepository.findByComponent(component);
	}

}
