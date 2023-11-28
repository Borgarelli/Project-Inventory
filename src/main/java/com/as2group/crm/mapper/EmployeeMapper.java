package com.as2group.crm.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.as2group.crm.dto.EmployeeRequest;
import com.as2group.crm.dto.EmployeeResponse;
import com.as2group.crm.model.Employee;

@Component
public class EmployeeMapper {
    
    public Employee map(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setName(employeeRequest.name());
        employee.setEmail(employeeRequest.email());
        employee.setGender(employeeRequest.gender());
        employee.setPassword(employeeRequest.password());
        return employee;
    }

    public EmployeeResponse map(Employee employee) {
        return new EmployeeResponse(employee.getId(), employee.getName(), employee.getEmail(), employee.getGender());
    }

    public List<EmployeeResponse> map(List<Employee> employees) {
        List<EmployeeResponse> response = new ArrayList<>();
        for(Employee employee : employees){
            response.add(new EmployeeResponse(employee.getId(), employee.getName(), employee.getEmail(), employee.getGender()));
        }
        return response;
    }
}
