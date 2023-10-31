package com.as2group.crm.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.enumeration.ComputerStatus;
import com.as2group.crm.enumeration.EmployeeStatus;
import com.as2group.crm.model.Computer;
import com.as2group.crm.model.ComputerEmployee;
import com.as2group.crm.model.Employee;
import com.as2group.crm.repository.ComputerEmployeeRepository;
import com.as2group.crm.repository.EmployeeRepository;

@SpringBootTest
public class EmployeeTest {
    
    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private ComputerEmployeeRepository computerEmployeeRepository;

    @BeforeEach
    public void setUp() {

        Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Kauã Borgarelli");
		employee.setEmail("kaua1as74@group");
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setStatus(EmployeeStatus.ATIVO);
		employee.setEntryDate(LocalDate.now());

		Employee employeeInactive = new Employee();
		employeeInactive.setId(2L);
		employeeInactive.setName("Kauã Borgarelli");
		employeeInactive.setEmail("kaua1as74@group");
		employeeInactive.setTelephone("12992002060");
		employeeInactive.setGender("Masculino");
		employeeInactive.setStatus(EmployeeStatus.INATIVO);
		employeeInactive.setEntryDate(LocalDate.now());

		Employee employee2 = new Employee();
		employee2.setId(3L);
		employee2.setName("Kauã Borgarelli");
		employee2.setEmail("kaua1as74@group");
		employee2.setTelephone("12992002060");
		employee2.setGender("Masculino");
		employee2.setStatus(EmployeeStatus.ATIVO);
		employee2.setEntryDate(LocalDate.now());

        Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19188");
		computer.setSn("14719733488");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		computer.setStatus(ComputerStatus.EM_USO);
		computer.setEmployee(employee);
		computer.setEntryDate(LocalDate.now());

        ComputerEmployee computerEmployee = new ComputerEmployee();
		computerEmployee.setId_comp_empl(1L);
		computerEmployee.setComputer(computer);
		computerEmployee.setEmployee(employee);
		computerEmployee.setReceived(LocalDateTime.now());

        Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
        Mockito.when(employeeRepository.findById(2L)).thenReturn(Optional.of(employeeInactive));
        Mockito.when(employeeRepository.findById(3L)).thenReturn(Optional.of(employee2));

        Mockito.when(employeeRepository.findByEmail("kaua1as74@group")).thenReturn(Optional.of(employee));

        Mockito.when(computerEmployeeRepository.findByEmployee(any())).thenReturn(List.of(computerEmployee));
        
    }

    @Test
    public void createEmployeeOkTest() {
        Employee employee = new Employee();
		employee.setName("Kauã Borgarelli");
		employee.setEmail("kauaas2@group");
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setStatus(EmployeeStatus.ATIVO);
		employee.setEntryDate(LocalDate.now());

        assertDoesNotThrow(() -> {
            employeeService.create(employee);
        });
    }

    @Test
    public void createEmployeeUniqueEmailNOkTest() {
        Employee employee = new Employee();
		employee.setName("Kauã Borgarelli");
		employee.setEmail("kaua1as74@group");
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setStatus(EmployeeStatus.ATIVO);
		employee.setEntryDate(LocalDate.now());

        assertThrows(ResponseStatusException.class, () -> {
            employeeService.create(employee);
        });
    }

    @Test
    public void createEmployeeNullNOkTest() {
        Employee employee = new Employee();
		employee.setName("Kauã Borgarelli");
		employee.setEmail(null);
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setStatus(EmployeeStatus.ATIVO);
		employee.setEntryDate(LocalDate.now());

        assertThrows(IllegalArgumentException.class, () -> {
            employeeService.create(employee);
        });
    }

    @Test
    public void createEmployeeEmptyNOkTest() {
        Employee employee = new Employee();
		employee.setName("Kauã Borgarelli");
		employee.setEmail("");
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setStatus(EmployeeStatus.ATIVO);
		employee.setEntryDate(LocalDate.now());

        assertThrows(IllegalArgumentException.class, () -> {
            employeeService.create(employee);
        });
    }

    @Test
    public void editEmployeeOkTest() {
        Employee employee = new Employee();
		employee.setName("Kauã Borgarelli");
		employee.setEmail("kauaas2@group");
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setStatus(EmployeeStatus.ATIVO);
		employee.setEntryDate(LocalDate.now());

        assertDoesNotThrow(() -> {
            employeeService.edit(employee, 1L);
        });
    }

    @Test
    public void editEmployeeEmailUniqueNOkTest() {
        Employee employee = new Employee();
		employee.setName("Kauã Borgarelli");
		employee.setEmail("kaua1as74@group");
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setStatus(EmployeeStatus.ATIVO);
		employee.setEntryDate(LocalDate.now());

        assertThrows(ResponseStatusException.class, () -> {
            employeeService.edit(employee, 1L);
        });
    }

    @Test
    public void editEmployeeEmailNullNOkTest() {
        Employee employee = new Employee();
		employee.setName("Kauã Borgarelli");
		employee.setEmail(null);
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setStatus(EmployeeStatus.ATIVO);
		employee.setEntryDate(LocalDate.now());

        assertThrows(IllegalArgumentException.class, () -> {
            employeeService.edit(employee, 1L);
        });
    }

    @Test
    public void editEmployeeEmailEmptyNOkTest() {
        Employee employee = new Employee();
		employee.setName("Kauã Borgarelli");
		employee.setEmail("");
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setStatus(EmployeeStatus.ATIVO);
		employee.setEntryDate(LocalDate.now());

        assertThrows(IllegalArgumentException.class, () -> {
            employeeService.edit(employee, 1L);
        });
    }
}
