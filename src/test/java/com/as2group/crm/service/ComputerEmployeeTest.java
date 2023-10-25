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
import com.as2group.crm.repository.ComputerRepository;
import com.as2group.crm.repository.EmployeeRepository;



@SpringBootTest
public class ComputerEmployeeTest {
	
	@Autowired
	private ComputerEmployeeService computerEmployeeService;
	
	@MockBean
	private ComputerEmployeeRepository computerEmployeeRepository;

	@MockBean
	private ComputerRepository computerRepository;

	@MockBean
	private EmployeeRepository employeeRepository;
	
	
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
		computer.setPatrimony("NTK19188");
		computer.setSn("14719733488");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		computer.setStatus(ComputerStatus.PRA_USO);
		computer.setEntryDate(LocalDate.now());

		Computer computerReadyToUse = new Computer();
		computerReadyToUse.setId(2L);
		computerReadyToUse.setPatrimony("NTK19188");
		computerReadyToUse.setSn("14719733488");
		computerReadyToUse.setSector("IT");
		computerReadyToUse.setModel("Inspiron 14R 5437");
		computerReadyToUse.setBrand("Dell");
		computerReadyToUse.setSoCurrent("Ubuntu 22.04.2 LTS");
		computerReadyToUse.setSoOriginal("Windows 10");
		computerReadyToUse.setEmployee(employee);
		computerReadyToUse.setStatus(ComputerStatus.EM_USO);
		computerReadyToUse.setEntryDate(LocalDate.now());

		Computer computerInactive = new Computer();
		computerInactive.setId(3L);
		computerInactive.setPatrimony("NTK19187");
		computerInactive.setSn("14719733487");
		computerInactive.setSector("IT");
		computerInactive.setModel("Inspiron 14R 5437");
		computerInactive.setBrand("Dell");
		computerInactive.setSoCurrent("Ubuntu 22.04.2 LTS");
		computerInactive.setSoOriginal("Windows 10");
		computerInactive.setStatus(ComputerStatus.INATIVO);
		computerInactive.setEntryDate(LocalDate.now());
		
		ComputerEmployee computerEmployee = new ComputerEmployee();
		computerEmployee.setId_comp_empl(1L);
		computerEmployee.setComputer(computerReadyToUse);
		computerEmployee.setEmployee(employee);
		computerEmployee.setReceived(LocalDateTime.now());
		
		Mockito.when(computerEmployeeRepository.findById(1L)).thenReturn(Optional.of(computerEmployee));
		Mockito.when(computerRepository.findById(1L)).thenReturn(Optional.of(computer));		
		Mockito.when(computerRepository.findById(2L)).thenReturn(Optional.of(computerReadyToUse));

		Mockito.when(computerRepository.save(any())).thenReturn(computer);

		Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		

		Mockito.when(computerEmployeeRepository.save(any())).thenReturn(computerEmployee);
		Mockito.when(computerEmployeeRepository.findByComputer(any())).thenReturn(
				List.of(new ComputerEmployee()));
	    Mockito.when(computerEmployeeRepository.findByComputerAndEmployee(computerReadyToUse, employee))
        .thenReturn(List.of(computerEmployee));
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

		Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		Mockito.when(computerRepository.findById(1L)).thenReturn(Optional.of(computer));
		
		ComputerEmployee computerEmployee = new ComputerEmployee();
		computerEmployee.setComputer(computer);
		computerEmployee.setEmployee(employee);
		computerEmployee.setReceived(LocalDateTime.now());
		
		assertDoesNotThrow(() -> {
			computerEmployeeService.link(computer.getId(), employee.getId());
		});
		
	}
	
	@Test
	public void unlinkComputerEmployeeOkTest() {
		assertDoesNotThrow(() -> {
			computerEmployeeService.unlink(2L,1L);
		});		
	}
	
	@Test
	public void unlinkComputerReadyUseEmployeeNokTest() {
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
	public void linkComputerLinkedNewEmployeeNokTest() {
		Employee employee = new Employee();
		employee.setId(1L);
		employee.setName("Kauã Borgarelli");
		employee.setEmail("kaua1as74@group");
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setStatus(EmployeeStatus.ATIVO);
		employee.setEntryDate(LocalDate.now());

		Computer computer = computerEmployeeService.findById(1L).getComputer();

		ComputerEmployee computerEmployee = new ComputerEmployee();
		computerEmployee.setId_comp_empl(1L);
		computerEmployee.setComputer(computer);
		computerEmployee.setEmployee(employee);
		computerEmployee.setReceived(LocalDateTime.now());

		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.link(computer.getId(), employee.getId());
		});

	}

	@Test
	public void linkComputerInactiveNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.link(2L, 1L);
		});
	}
	
	@Test
	public void historicComputerOkTest() {
	    List<ComputerEmployee> computerEmployees = computerEmployeeService.historicComputer(1L);
	    assertEquals(1, computerEmployees.size());
	}
	
	@Test
	public void findComputerByIdOkTest() {
		assertEquals(2L, computerEmployeeService.findById(1L).getComputer().getId());
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
