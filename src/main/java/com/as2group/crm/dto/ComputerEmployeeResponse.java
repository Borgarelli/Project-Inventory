package com.as2group.crm.dto;

import java.time.LocalDateTime;

public record ComputerEmployeeResponse (
    Long id_comp_empl, 
    EmployeeResponse employee, 
    LocalDateTime received, 
    LocalDateTime returned ) {
    
}
