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
		
		Computer computerReadyToUse = new Computer();
		computerReadyToUse.setId(1L);
		computerReadyToUse.setPatrimony("NTK19188");
		computerReadyToUse.setSn("14719733488");
		computerReadyToUse.setSector("IT");
		computerReadyToUse.setModel("Inspiron 14R 5437");
		computerReadyToUse.setBrand("Dell");
		computerReadyToUse.setSoCurrent("Ubuntu 22.04.2 LTS");
		computerReadyToUse.setSoOriginal("Windows 10");
		computerReadyToUse.setStatus(ComputerStatus.PRA_USO);
		computerReadyToUse.setEntryDate(LocalDate.now());

		Computer computerInUse = new Computer();
		computerInUse.setId(2L);
		computerInUse.setPatrimony("NTK19188");
		computerInUse.setSn("14719733488");
		computerInUse.setSector("IT");
		computerInUse.setModel("Inspiron 14R 5437");
		computerInUse.setBrand("Dell");
		computerInUse.setSoCurrent("Ubuntu 22.04.2 LTS");
		computerInUse.setSoOriginal("Windows 10");
		computerInUse.setStatus(ComputerStatus.EM_USO);
		computerInUse.setEmployee(employee);
		computerInUse.setEntryDate(LocalDate.now());

		Computer computerInactive = new Computer();
		computerInactive.setId(3L);
		computerInactive.setPatrimony("NTK19188");
		computerInactive.setSn("14719733488");
		computerInactive.setSector("IT");
		computerInactive.setModel("Inspiron 14R 5437");
		computerInactive.setBrand("Dell");
		computerInactive.setSoCurrent("Ubuntu 22.04.2 LTS");
		computerInactive.setSoOriginal("Windows 10");
		computerInactive.setStatus(ComputerStatus.INATIVO);
		computerInactive.setEntryDate(LocalDate.now());
		
		ComputerEmployee computerEmployee = new ComputerEmployee();
		computerEmployee.setId_comp_empl(1L);
		computerEmployee.setComputer(computerInUse);
		computerEmployee.setEmployee(employee);
		computerEmployee.setReceived(LocalDateTime.now());

		ComputerEmployee computerEmployeeDiferent = new ComputerEmployee();
		computerEmployeeDiferent.setId_comp_empl(2L);
		computerEmployeeDiferent.setComputer(computerInUse);
		computerEmployeeDiferent.setEmployee(employee2);
		computerEmployeeDiferent.setReceived(LocalDateTime.now());
		
		Mockito.when(computerEmployeeRepository.findById(1L)).thenReturn(Optional.of(computerEmployee));
		Mockito.when(computerEmployeeRepository.findById(2L)).thenReturn(Optional.of(computerEmployeeDiferent));

		Mockito.when(computerRepository.findById(1L)).thenReturn(Optional.of(computerReadyToUse));		
		Mockito.when(computerRepository.findById(2L)).thenReturn(Optional.of(computerInUse));
		Mockito.when(computerRepository.findById(3L)).thenReturn(Optional.of(computerInactive));

		Mockito.when(computerRepository.save(any())).thenReturn(computerReadyToUse);

		Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));		
		Mockito.when(employeeRepository.findById(2L)).thenReturn(Optional.of(employeeInactive));
		Mockito.when(employeeRepository.findById(3L)).thenReturn(Optional.of(employee2));
		

		Mockito.when(computerEmployeeRepository.save(any())).thenReturn(computerEmployee);
		Mockito.when(computerEmployeeRepository.findByComputer(any())).thenReturn(
				List.of(new ComputerEmployee()));
		Mockito.when(computerEmployeeRepository.findByEmployee(any())).thenReturn(
				List.of(new ComputerEmployee()));
	    Mockito.when(computerEmployeeRepository.findByComputerAndEmployee(computerInUse, employee))
        .thenReturn(List.of(computerEmployee));
	}
	
	
	@Test
	public void newComputerEmployeeOkTest() {
		Employee employee = new Employee();
		employee.setName("Kauã Borgarelli");
		employee.setEmail("kaua1as74@group");
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setStatus(EmployeeStatus.ATIVO);
		employee.setEntryDate(LocalDate.now());
		
		Computer computer = new Computer();
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
		computerEmployee.setComputer(computer);
		computerEmployee.setEmployee(employee);
		computerEmployee.setReceived(LocalDateTime.now());

		Mockito.when(computerRepository.findById(1L)).thenReturn(Optional.of(computer));
		Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		Mockito.when(computerEmployeeRepository.save(any())).thenReturn(computerEmployee);

		assertDoesNotThrow(() -> {
			computerEmployeeService.link(1L, 1L);
		});
		
	}
	
	//Link Test
	@Test
	public void linkComputerOnEmployeeOkTest() {
		assertDoesNotThrow(() -> {
			computerEmployeeService.link(1L, 1L);
		});
	}

	@Test
	public void linkComputerEmployeeInactiveNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.link(1L, 2L);
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

		Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.link(2L, 1L);
		});

	}

	@Test
	public void linkComputerInactiveNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.link(3L, 1L);
		});
	}

	@Test
	public void linkComputerNullNOk() {
		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.link(null, 1L);
		});
	}
	@Test
	public void linkEmployeeNullNOk() {
		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.link(1L, null);
		});
	}

	//Unlink Tests
	@Test
	public void unlinkComputerEmployeeOkTest() {
		assertDoesNotThrow(() -> {
			computerEmployeeService.unlink(2L,1L);
		});		
	}

	@Test
	public void unlinkComputerEmployeeDifferentNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.unlink(2L, 3L);
		});
	}
	
	@Test
	public void unlinkComputerReadyUseEmployeeNokTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.unlink(1L, 1L);
		});
	}

	@Test
	public void unlinkComputerNullNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.unlink(null, 1L);
		});
	}

	@Test
	public void unlinkEmployeeNullNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.unlink(2L, null);
		});
	}

	@Test
	public void unlinkComputerReturnedNOkTest() {
		Employee employee = new Employee();
		employee.setName("Kauã Borgarelli");
		employee.setEmail("kaua1as74@group");
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setStatus(EmployeeStatus.ATIVO);
		employee.setEntryDate(LocalDate.now());
		
		Computer computer = new Computer();
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
		computerEmployee.setComputer(computer);
		computerEmployee.setEmployee(employee);
		computerEmployee.setReceived(LocalDateTime.now());
		computerEmployee.setReturned(LocalDateTime.now());

		Mockito.when(computerRepository.findById(1L)).thenReturn(Optional.of(computer));
		Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
		Mockito.when(computerEmployeeRepository.findByComputerAndEmployee(computer, employee)).thenReturn(List.of(computerEmployee));

		assertThrows(IllegalArgumentException.class, () -> {
			computerEmployeeService.unlink(1L, 1L);
		});
	}
	//Get Tests
	@Test
	public void historicComputerOkTest() {
	    List<ComputerEmployee> computerEmployees = computerEmployeeService.historicComputer(1L);
	    assertEquals(1, computerEmployees.size());
	}
	
	@Test
	public void historicEmployeeOkTest() {
		List<ComputerEmployee> computerEmployees = computerEmployeeService.historicEmployee(1L);
		assertEquals(1, computerEmployees.size());
	}

	@Test
	public void findComputerByIdOkTest() {
		assertEquals(2L, computerEmployeeService.findById(1L).getComputer().getId());
	}
	
	@Test
	public void findByIdNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.findById(3L);
		});
	}
	
	@Test
	public void findByEmployeeIdOkTest() {
		assertEquals(1L, computerEmployeeService.findById(1L).getEmployee().getId());
	}
	
	@Test
	public void findByEmployeeIdNokTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerEmployeeService.findById(3L).getEmployee().getId();
		});
	}

}
