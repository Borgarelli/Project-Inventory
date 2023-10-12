package com.as2group.crm.dto;

import com.as2group.crm.model.Components;
import com.as2group.crm.model.Computer;

public record ComputerComponentsRequest(Computer computer, Components components) {
    
}
