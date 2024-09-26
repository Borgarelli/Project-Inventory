package com.as2group.crm.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.server.ResponseStatusException;

import com.as2group.crm.enums.Status;
import com.as2group.crm.model.Computer;
import com.as2group.crm.model.Employee;
import com.as2group.crm.repository.ComputerRepository;

@SpringBootTest
public class ComputerTest {

	@Autowired
	private ComputerService computerService;
	
	@MockBean
	private ComputerRepository computerRepository;
	
	@BeforeEach
	public void setUp() {

		Employee employee = new Employee();
		employee.setName("Kauã Borgarelli");
		employee.setEmail("kaua1as74@group");
		employee.setTelephone("12992002060");
		employee.setGender("Masculino");
		employee.setStatus(Status.ATIVO);
		employee.setEntryDate(LocalDate.now());

		Computer computerReadyToUse = new Computer();
		computerReadyToUse.setId(1L);
		computerReadyToUse.setPatrimony("NTK19199");
		computerReadyToUse.setSn("14719733499");
		computerReadyToUse.setSector("IT");
		computerReadyToUse.setModel("Inspiron 14R 5437");
		computerReadyToUse.setBrand("Dell");
		computerReadyToUse.setSoCurrent("Ubuntu 22.04.2 LTS");
		computerReadyToUse.setSoOriginal("Windows 10");
		computerReadyToUse.setStatus(Status.PRA_USO);
		computerReadyToUse.setEntryDate(LocalDate.now());

		Computer computerInUse = new Computer();
		computerInUse.setId(2L);
		computerInUse.setPatrimony("NTK19199");
		computerInUse.setSn("14719733499");
		computerInUse.setSector("IT");
		computerInUse.setModel("Inspiron 14R 5437");
		computerInUse.setBrand("Dell");
		computerInUse.setSoCurrent("Ubuntu 22.04.2 LTS");
		computerInUse.setSoOriginal("Windows 10");
		computerInUse.setStatus(Status.EM_USO);
		computerInUse.setEmployee(employee);
		computerInUse.setEntryDate(LocalDate.now());


		Computer computerInactive = new Computer();
		computerInactive.setId(3L);
		computerInactive.setPatrimony("NTK19199");
		computerInactive.setSn("14719733499");
		computerInactive.setSector("IT");
		computerInactive.setModel("Inspiron 14R 5437");
		computerInactive.setBrand("Dell");
		computerInactive.setSoCurrent("Ubuntu 22.04.2 LTS");
		computerInactive.setSoOriginal("Windows 10");
		computerInactive.setStatus(Status.INATIVO);
		computerInactive.setEntryDate(LocalDate.now());

		List<Computer> computers = new ArrayList<>();
		computers.add(computerReadyToUse);

		Mockito.when(computerRepository.findById(1L)).thenReturn(Optional.of(computerReadyToUse));
		Mockito.when(computerRepository.findByPatrimony("NTK19199")).thenReturn(Optional.of(computerReadyToUse));
		Mockito.when(computerRepository.findByPatrimony("NTK19187")).thenReturn(Optional.empty());
		

		Mockito.when(computerRepository.findBySn("14719733499")).thenReturn(Optional.of(computerReadyToUse));

		Mockito.when(computerRepository.findById(2L)).thenReturn(Optional.of(computerInUse));
		Mockito.when(computerRepository.findById(3L)).thenReturn(Optional.of(computerInactive));
		
		Mockito.when(computerRepository.findAllByStatus(Status.PRA_USO)).thenReturn(Collections.singletonList(computerReadyToUse));
		Mockito.when(computerRepository.findAllByStatus(Status.EM_USO)).thenReturn(Collections.singletonList(computerInUse));

		Mockito.when(computerRepository.findByStatusActivate()).thenReturn(Collections.singletonList(computerInUse));


		// Mockito.when(computerRepository.save(any())).thenReturn(computerReadyToUse);
		// Mockito.when(computerRepository.save(any())).thenReturn(computerInUse);
		// Mockito.when(computerRepository.save(any())).thenReturn(computerInactive);
		
		Mockito.when(computerRepository.findAll()).thenReturn(computers);
		

	}
	
	//Creation Tests
	@Test
	public void createComputerOkTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19180");
		computer.setSn("14719733480");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");

		Mockito.when(computerRepository.save(any())).thenReturn(computer);

		assertDoesNotThrow(() -> {
			computerService.create(computer);
		});
	}
	
	@Test
	public void createPatrimonyUniqueNOkTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19199");
		computer.setSn("14719733480");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		assertThrows(ResponseStatusException.class, () -> {
			computerService.create(computer);
		});
	}
	
	@Test
	public void createComputerPatrimonyNullNokTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony(null);
		computer.setSn("14719733480");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		assertThrows(IllegalArgumentException.class, () -> {
			computerService.create(computer);
		});
	}
	
	@Test
	public void createComputerSnNullNOkTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19180");
		computer.setSn(null);
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		assertThrows(IllegalArgumentException.class, () -> {
			computerService.create(computer);
		});
	}

	@Test
	public void createComputerSnUniqueNOkTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19180");
		computer.setSn("14719733499");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");

		assertThrows(ResponseStatusException.class, () -> {
			computerService.create(computer);
		});
	}

	@Test
	public void createComputerPatrimonyEmptyNOkTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("");
		computer.setSn("14719733480");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		assertThrows(IllegalArgumentException.class, () -> {
			computerService.create(computer);
		});
	}

	@Test
	public void createComputerSnEmptyNOkTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19180");
		computer.setSn("");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");

		assertThrows(IllegalArgumentException.class, () -> {
			computerService.create(computer);
		});
	}

	//EditTests
	@Test
	public void editComputerOkTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19188");
		computer.setSn("14719733488");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		assertDoesNotThrow(() -> {
			computerService.edit(1L, computer);
		});
	}

	@Test
	public void editComputerUndefinedNOkTest() {
		Computer computer = new Computer();
		computer.setPatrimony("NTK19188");
		computer.setSn("14719733488");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		assertThrows(ResponseStatusException.class, () -> {
			computerService.edit(null, computer);
		});
	}

	@Test
	public void editComputerPatrimonyOkTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19188");
		computer.setSn("14719733480");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		assertDoesNotThrow(() -> {
			computerService.edit(1L, computer);
		});
	}

	@Test
	public void editComputerPatrimonyUniqueNOkTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19199");
		computer.setSn("14719733480");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		assertThrows(ResponseStatusException.class, () -> {
			computerService.edit(1L, computer);
		});
	}

	@Test
	public void editComputerPatrimonyNullNokTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony(null);
		computer.setSn("14719733480");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");

		assertThrows(IllegalArgumentException.class, () -> {
			computerService.edit(1L, computer);
		});
	}

	@Test
	public void editComputerPatrimonyEmptyNOkTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("");
		computer.setSn("14719733480");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		assertThrows(IllegalArgumentException.class, () -> {
			computerService.edit(1L, computer);
		});
	}


	@Test
	public void editComputerSnOkTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19180");
		computer.setSn("14719733488");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		assertDoesNotThrow(() -> {
			computerService.edit(1L, computer);
		});
	}

	@Test
	public void editComputerSnUniqueNOkTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19180");
		computer.setSn("14719733499");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		assertThrows(ResponseStatusException.class, () -> {
			computerService.edit(1L, computer);
		});
	}

	@Test
	public void editComputerSnNullNOkTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19180");
		computer.setSn(null);
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		assertThrows(IllegalArgumentException.class, () -> {
			computerService.edit(1L, computer);
		});
	}

	@Test
	public void editComputerSnEmptyNOkTest() {
		Computer computer = new Computer();
		computer.setId(1L);
		computer.setPatrimony("NTK19180");
		computer.setSn("");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		assertThrows(IllegalArgumentException.class, () -> {
			computerService.edit(1L, computer);
		});
	}
	
	//DeleteTests
	@Test
	public void deleteComputerOkTest() {
		assertDoesNotThrow(() -> {
			computerService.delete(1L);
		});
	}

	@Test
	public void deleteComputerByIdNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerService.delete(4L);
		});
	}

	//InactiveTests
	@Test
	public void inactivateComputerOkTest() {
		assertDoesNotThrow(() -> {
			computerService.inactivate(1L);
		});
	}

	@Test
	public void inactivateComputerWithEmployeeNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerService.inactivate(2L);
		});
	}

	@Test
	public void inactivateComputerInactiveNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerService.inactivate(3L);
		});
	}

	@Test
	public void inactiveComputerUndefinedNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerService.inactivate(null);
		});
	}

	@Test
	public void activateComputerOkTest() {
		assertDoesNotThrow(()-> {
			computerService.activate(3L);
		});
	}

	@Test
	public void activateComputerActiveNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerService.activate(1L);
		});
	}

	@Test
	public void activateComputerInUseNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerService.activate(2L);
		});
	}

	@Test
	public void activateComputerUndefinedNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerService.activate(null);
		});
	}

	@Test
	public void findbyStatusOkTest() {
		assertEquals(1, computerService.listByStatus(Status.EM_USO).size());
	}

	@Test
	public void stockOkTest() {
		assertEquals(1, computerService.stock().size());
	}

	//GetTests
	@Test
	public void findComputerByIdNokTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerService.show(4L);
		});
	}
	
	@Test
	public void findPatrimonyNOkTest() {
		assertThrows(ResponseStatusException.class, () -> {
			computerService.show("NTK19187");
		});
	}

	@Test
	public void findPatrimonyByIdOkTest() {
		assertEquals("NTK19199", computerService.show(1L).getPatrimony());
	}

	@Test
	public void findPatrimonysByIdOkTest() {
		assertEquals("NTK19199", computerService.show("NTK19199").getPatrimony());
	}
	
	
	@Test
	public void findSnByIdOkTest() {
		assertEquals("14719733499", computerService.show(1L).getSn());
	}
	

	@Test
	public void AllComputerOkTest() {
		assertEquals(1, computerService.list().size());
	}

	
}
