package com.as2group.crm.mapper;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.as2group.crm.dto.ComputerEmployeeRequest;
import com.as2group.crm.dto.ComputerEmployeeResponse;

import com.as2group.crm.dto.EmployeeResponse;
import com.as2group.crm.model.ComputerEmployee;

@Component
public class ComputerEmployeeMapper {
    @Autowired
    EmployeeMapper employeeMapper;
    
    public ComputerEmployee map(ComputerEmployeeRequest computerEmployeeRequest) {
        ComputerEmployee computerEmployee = new ComputerEmployee();
        computerEmployee.setComputer(computerEmployeeRequest.computer());
        computerEmployee.setEmployee(computerEmployeeRequest.employee());
        return computerEmployee;
    }

    public List<ComputerEmployeeResponse> map(List<ComputerEmployee> computerEmployees) {
        List<ComputerEmployeeResponse> response = new ArrayList<>();
        for(ComputerEmployee computerEmployee : computerEmployees) {
            EmployeeResponse employee = this.employeeMapper.map(computerEmployee.getEmployee());
            
            response.add( new ComputerEmployeeResponse(computerEmployee.getId_comp_empl(), employee, computerEmployee.getReceived(), computerEmployee.getReturned()));
            
        }
        return response;
    }
}
