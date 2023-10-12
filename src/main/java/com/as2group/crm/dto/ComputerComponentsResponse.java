package com.as2group.crm.dto;

import java.time.LocalDateTime;

public record ComputerComponentsResponse(
    ComputerResponse computer, 
    LocalDateTime received, 
    LocalDateTime returned) {
    
}
