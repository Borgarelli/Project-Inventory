package com.as2group.crm.enumeration;

public enum ComponentsStatus {
    PRA_USO("Component is ready to use"),
    EM_USO("Component is already on a computer"),
    INATIVO("Component is inactive");

    private String description;

    private ComponentsStatus(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
