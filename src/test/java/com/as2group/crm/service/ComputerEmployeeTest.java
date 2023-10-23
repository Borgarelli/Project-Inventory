package com.as2group.crm.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

@SpringBootTest
public class ComputerEmployeeTest {

    @Autowired
    private ComputerEmployeeService computerEmployeeService;

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
		
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19199");
		computer.setSn("14719733499");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		computer.setStatus(ComputerStatus.PRA_USO);
		computer.setEntryDate(LocalDate.now());
		
		ComputerEmployee computerEmployee = new ComputerEmployee();
		computerEmployee.setId_comp_empl(1L);
		computerEmployee.setComputer(computer);
		computerEmployee.setEmployee(employee);
		computerEmployee.setReceived(LocalDateTime.now());
		
		Mockito.when(computerEmployeeRepository.findById(1L)).thenReturn(Optional.of(computerEmployee));
		Mockito.when(computerEmployeeRepository.save(any())).thenReturn(computerEmployee);
		Mockito.when(computerEmployeeRepository.findByComputer(any())).thenReturn(
				List.of(new ComputerEmployee()));
	    Mockito.when(computerEmployeeRepository.findByComputerAndEmployee(any(), any()))
        .thenReturn(List.of(new ComputerEmployee()));
	}
	
	
	@Test
	public void newComputerEmployeeOkTest() {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Kauã Borgarelli");
		employee.setEmail("kaua1as74@group");
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setStatus(EmployeeStatus.ATIVO);
		employee.setEntryDate(LocalDate.now());
		
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19199");
		computer.setSn("14719733499");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		computer.setStatus(ComputerStatus.PRA_USO);
		computer.setEntryDate(LocalDate.now());
		
		ComputerEmployee computerEmployee = new ComputerEmployee();
		computerEmployee.setId_comp_empl(1L);
		computerEmployee.setComputer(computer);
		computerEmployee.setEmployee(employee);
		computerEmployee.setReceived(LocalDateTime.now());
		
		assertDoesNotThrow(() -> {
			computerEmployeeService.link(computer.getId(), employee.getId());
		});
		
	}
	
	@Test
	public void unlinkComputerEmployeeOkTest() {
		
		Computer computer = computerEmployeeService.findById(1L).getComputer();
		Employee employee = computerEmployeeService.findById(1L).getEmployee();
		assertDoesNotThrow(() -> {
			computerEmployeeService.unlink(computer.getId(), employee.getId());
		});		
	}

    @Test
    public void unlinkComputerReadyNokTest() {
        Employee employee = computerEmployeeService.findById(1L).getEmployee();

        Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19199");
		computer.setSn("14719733499");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		computer.setStatus(ComputerStatus.PRA_USO);
		computer.setEntryDate(LocalDate.now());

        assertThrows(ResponseStatusException.class, () -> {
            computerEmployeeService.unlink(computer.getId(), employee.getId());
        });
    }
	
	
	@Test
	public void historicComputerOkTest() {
	    List<ComputerEmployee> computerEmployees = computerEmployeeService.historicComputer(1L);
	    assertEquals(1, computerEmployees.size());
	}
	
	@Test
	public void findComputerByIdOkTest() {
		assertEquals(1L, computerEmployeeService.findById(1L).getComputer().getId());
	}
	
	@Test
	public void findByIdNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.findById(2L).getComputer().getId();
		});
	}
	
	@Test
	public void findByEmployeeIdOkTest() {
		assertEquals(1L, computerEmployeeService.findById(1L).getEmployee().getId());
	}
	
	@Test
	public void findByEmployeeIdNokTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.findById(2L).getEmployee().getId();
		});
	}

}
