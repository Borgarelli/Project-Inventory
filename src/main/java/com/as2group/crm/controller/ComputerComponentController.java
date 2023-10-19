package com.as2group.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.as2group.crm.dto.ComputerComponentsResponse;
import com.as2group.crm.mapper.ComputerComponentsMapper;
import com.as2group.crm.model.ComputerComponent;
import com.as2group.crm.service.ComputerComponentService;

@RestController
@RequestMapping("/api")
public class ComputerComponentController {
	
	@Autowired
	private ComputerComponentService computerComponentService;

	@Autowired
	private ComputerComponentsMapper computerComponentsMapper;
	
	@PostMapping("/computers/{computerId}/components/{componentId}")
	@ResponseStatus(HttpStatus.CREATED)
	public ComputerComponent create(@PathVariable("computerId") Long computerId,
			@PathVariable("componentId") Long componentId) {
		return computerComponentService.link(computerId, componentId);
	}
	
	@DeleteMapping("/computers/{computerId}/components/{componentId}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("computerId") Long computerId,
			@PathVariable("componentId") Long componentId) {
	computerComponentService.unlink(computerId, componentId);
	}

	@GetMapping("/components/{componentId}/historic")
	public List<ComputerComponentsResponse> historicComponent(@PathVariable("componentId") long componentId) {
		return computerComponentsMapper.map(computerComponentService.historicComponent(componentId));

	}
}
