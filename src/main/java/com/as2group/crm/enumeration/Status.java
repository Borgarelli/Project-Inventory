package com.as2group.crm.enumeration;

import lombok.Getter;

@Getter
public enum Status {

    ATIVO("Is active"),
    INATIVO("Is inactiuve"),
    EM_USO("In use"),
    PRA_USO("Ready to Use");

    private String desccription;

    private  Status(String description){
        this.desccription = description;
    }
}
