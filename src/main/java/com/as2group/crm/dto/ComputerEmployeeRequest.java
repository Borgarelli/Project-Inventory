package com.as2group.crm.dto;

import com.as2group.crm.model.Computer;
import com.as2group.crm.model.Employee;

public record ComputerEmployeeRequest(Computer computer, Employee employee) {
    
}
