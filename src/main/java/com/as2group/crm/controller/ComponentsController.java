package com.as2group.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.as2group.crm.dto.ComponentsRequest;
import com.as2group.crm.dto.ComponentsResponse;
import com.as2group.crm.enumeration.ComponentsStatus;
import com.as2group.crm.mapper.ComponentsMapper;
import com.as2group.crm.model.Components;

import com.as2group.crm.service.ComponentsService;

@RestController
@RequestMapping("/api")
public class ComponentsController {

	@Autowired
	ComponentsService componentsService;

	@Autowired
	ComponentsMapper componentsMapper;

	@GetMapping("/components")
	public List<ComponentsResponse> list() {
		return componentsMapper.map(componentsService.list());
	}

	@GetMapping("/components/status/{status}")
	public List<ComponentsResponse> listByStatus(@PathVariable("status") ComponentsStatus status) {
		return componentsMapper.map(componentsService.listByStatus(status));
	}
	
	@GetMapping("/components/{id}")
	public ComponentsResponse show(@PathVariable("id") Long id) {
		return componentsMapper.map(componentsService.show(id));
	}
//
	@GetMapping("/components/patrimony/{patrimony}")
	public Components show(@PathVariable("patrimony") String patrimony) {
		return componentsService.show(patrimony);
	}
	
//	@GetMapping("/computers/type/{type}")
//	public List<Components> listByType(@PathVariable("type") ComponentType componentType) {
//		return componentsService.listByType(componentType);
//	}
	

	@PostMapping("/components")
	@ResponseStatus(HttpStatus.CREATED)
	public ComponentsResponse create(@RequestBody ComponentsRequest componentsRequest) {
		return componentsMapper.map(componentsService.create(componentsMapper.map(componentsRequest)));
	}

	@DeleteMapping("/components/{id}")
	public void delete(@PathVariable("id") Long id) {
		componentsService.inactivate(id);
	}
//	
//	@DeleteMapping("/components/{patrimony}")
//	public void delete(@PathVariable("patrimony") String patrimony ) {
//		componentsService.delete(patrimony);
//	}

//	@PutMapping("/computers/{id}")
//	public Components update(@PathVariable("id") Long id, @RequestBody Components components, Long patrimony,
//			String processor, String generation, String ram, String graphicsCard, String hd, String ssd) {
//		return componentsService.edit(components, id, patrimony, processor, generation, ram, graphicsCard, hd, ssd);
//	}

//	@PutMapping("/computers/{id}/inactivate")
//	public void inactivate(@PathVariable("id") Long id) {
//		computerEmployeeService.unlink(id, id);
//		componentsService.inactivate(id);
//	}

	@GetMapping("components/stock")
	public List<ComponentsResponse> stockByStatus() {
		return componentsMapper.map(componentsService.stock());
	}
//	
//	@GetMapping("components/type")
//	public Optional<ComponentType> stockstockByType() {
//		return componentsService.stockByType();
//	}

}
