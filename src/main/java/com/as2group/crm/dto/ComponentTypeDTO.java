package com.as2group.crm.dto;

import com.as2group.crm.enumeration.ComponentType;

public record ComponentTypeDTO(String name, String description) {

	public static ComponentTypeDTO of(ComponentType type) {
		return new ComponentTypeDTO(type.name(), type.getDescription());
	}
}
