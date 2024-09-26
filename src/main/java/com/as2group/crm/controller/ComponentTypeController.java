// package com.as2group.crm.controller;

// import java.util.ArrayList;
// import java.util.List;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import com.as2group.crm.dto.ComponentTypeDTO;
// import com.as2group.crm.enumeration.ComponentType;

// @RestController
// @RequestMapping("/api")
// public class ComponentTypeController {

// 	@GetMapping("/components/types")
// 	public List<ComponentTypeDTO> getTypes() {
// 		List<ComponentTypeDTO> typesDTO = new ArrayList<>();
// 		for (int i = 0; i < ComponentType.values().length; i++) {
// 			ComponentType componentType = ComponentType.values()[i];
// 			ComponentTypeDTO typeDTO = new ComponentTypeDTO(componentType.name(), componentType.getDescription());
// 			typesDTO.add(typeDTO);
// 		}
// 		return typesDTO;
// 	}
// }
