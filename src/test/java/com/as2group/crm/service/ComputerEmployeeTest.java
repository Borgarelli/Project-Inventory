package com.as2group.crm.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.as2group.crm.model.Computer;
import com.as2group.crm.model.ComputerEmployee;
import com.as2group.crm.model.Employee;
import com.as2group.crm.repository.ComputerEmployeeRepository;

@SpringBootTest
public class ComputerEmployeeTest {

    @Autowired
    private ComputerEmployeeService computerEmployeeService;

    @MockBean
    private ComputerEmployeeRepository computerEmployeeRepository;

    @Test
    public void createComputerEmployeeOkTest() {
        Computer computer = new Computer();
        computer.setId(1L);
        computer.setPatrimony("NTK191220");
        computer.setSn("14719733426");
        computer.setModel("Inspiron 14R 5437");
        computer.setBrand("Dell");
        computer.setSoCurrent("Ubuntu 22.04.2 LTS");
        computer.setSoOriginal("Windows 10");
        computer.setEntryDate(LocalDate.now());

        Employee employee = new Employee();
        employee.setId(1L);
		employee.setName("Kauã Borgarelli");
		employee.setEmail("kaua1as74@group");
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setEntryDate(LocalDate.now());

		ComputerEmployee computerEmployee = new ComputerEmployee();
        computerEmployee.setId_comp_empl(1L);
        computerEmployee.setComputer(computer);
        computerEmployee.setEmployee(employee);
        computerEmployee.setReceived(LocalDateTime.now());

        Mockito.when(computerEmployeeRepository.save(any())).thenReturn(computerEmployee);
        assertDoesNotThrow(() -> {
            computerEmployeeService.link(computer.getId(), employee.getId());
        });
    }
    
}
