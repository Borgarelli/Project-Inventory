package com.as2group.crm.enumeration;

public enum ComputerStatus {
	INATIVO("Computer is inactive"),
	PRA_USO("Computer is ready to use"),
	EM_USO("Computer in use");
	
	private String description;
	
	private ComputerStatus(String description) {
		this.description = description;
	}	
	
	public String getDescription() {
		return description;
	}
	
}
