package com.as2group.crm.enumeration;

public enum ComponentsStatus {
	INATIVO("Component is inactive"),
	PRA_USO("Component is ready to use"),
	EM_USO("Component is already in a computer");
    
    private String description;

    private ComponentsStatus(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
