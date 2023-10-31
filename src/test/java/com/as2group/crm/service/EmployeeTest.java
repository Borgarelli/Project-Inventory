package com.as2group.crm.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

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

    }
}
