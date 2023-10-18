package com.as2group.crm.dto;

import java.time.LocalDateTime;

public record ComputerComponentsResponse(
    ComputerSimpleResponse computer, 
    LocalDateTime received, 
    LocalDateTime returned) {
    
}
