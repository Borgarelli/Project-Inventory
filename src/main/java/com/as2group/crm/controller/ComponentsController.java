package com.as2group.crm.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.as2group.crm.dto.ComponentsRequest;
import com.as2group.crm.dto.ComponentsResponse;

import com.as2group.crm.enums.Status;
import com.as2group.crm.mapper.ComponentsMapper;
import com.as2group.crm.service.ComponentsService;
import com.as2group.crm.service.ComputerComponentService;

@RestController
@RequestMapping("/api")
public class ComponentsController {

	@Autowired
	ComponentsService componentsService;
	
	@Autowired
	ComponentsMapper componentMapper;
	
	@Autowired
	ComputerComponentService computerComponentService;

	@GetMapping("/components")
	public List<ComponentsResponse> list() {
		return componentMapper.map(componentsService.list());
	}

	@GetMapping("/components/status/{status}")
	public List<ComponentsResponse> listByStatus(@PathVariable("status") Status status) {
		return componentMapper.map(componentsService.listByStatus(status));
	}
	
	@GetMapping("/components/{id}")
	public ComponentsResponse show(@PathVariable("id") Long id) {
		return componentMapper.map(componentsService.show(id));
	}

	@GetMapping("/components/patrimony/{patrimony}")
	public ComponentsResponse show(@PathVariable("patrimony") String patrimony) {
		return componentMapper.map(componentsService.show(patrimony));
	}
	
	@GetMapping("/components/stock")
	public List<ComponentsResponse> stock() {
		return componentMapper.map(componentsService.stock());
	}
	

	@PostMapping("/components")
	@ResponseStatus(HttpStatus.CREATED)
	public ComponentsResponse create(@RequestBody ComponentsRequest componentsRequest) {
		return componentMapper.map(componentsService.create(componentMapper.map(componentsRequest)));
	}

	@DeleteMapping("/components/{id}/inactivate")
	public String delete(@PathVariable("id") Long id) {
		componentsService.inactivate(id);
		return "Component has been inactivate";
	}

	@PutMapping("/components/{id}/activate")
	public String activate(@PathVariable("id") Long id) {
		componentsService.activate(id);
		return "Component has been activate";
	}


}
