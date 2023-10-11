package com.as2group.crm.dto;
import com.as2group.crm.enumeration.EmployeeStatus;

public record EmployeeResponse (
    String name,
    String email,
    EmployeeStatus status,
    String sex
    )
    {
    
}
