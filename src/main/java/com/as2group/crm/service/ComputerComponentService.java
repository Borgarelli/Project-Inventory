package com.as2group.crm.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
	
    public ComputerComponent link(Long computerId, Long componentId) {
        Computer computer = computerService.show(computerId);
        Components component = componentService.show(componentId);


        if (computer.getStatus() == Computer.Status.INATIVO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Computer is not available");
        }

        if (component.getStatus() != Components.Status.PRA_USO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Component is not available");
        }

        ComputerComponent relationship = new ComputerComponent();
        relationship.setComponent(component);
        relationship.setComputer(computer);
        relationship.setReceived(LocalDateTime.now());

        computer.getComputerComponents().add(component);
        
        componentService.changeStatus(component, Components.Status.EM_USO);

        return computerComponentRepository.save(relationship);
    }

}
