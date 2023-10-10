package com.as2group.crm.enumeration;

public enum EmployeeStatus {
    INATIVO("Employee is inactive"),
    ATIVO("Employee is active");

    private String description;

    private EmployeeStatus(String description){
        this.description = description;
    }

    public String geDescription(){
        return description;
    }
}

