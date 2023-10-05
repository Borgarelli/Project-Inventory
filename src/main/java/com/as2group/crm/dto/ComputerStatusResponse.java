package com.as2group.crm.dto;

import com.as2group.crm.enumeration.ComputerStatus;

public record ComputerStatusResponse(String name, String description) {
	
	public static ComputerStatusResponse of(ComputerStatus status) {
		return new ComputerStatusResponse(status.name(), status.getDescription());
	}
}
