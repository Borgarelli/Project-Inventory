package com.as2group.crm.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.as2group.crm.enums.Status;
import com.as2group.crm.model.Computer;

@SpringBootTest
public class ComputerRepositoryTest {

	@Autowired
	private ComputerRepository computerRepository;
	
	@Test
	public void newComputerTest() {
		Computer computer = new Computer();
		computer.setPatrimony("NTK19120");
		computer.setSn("14719733420");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 20.04.2 LTS");
		computer.setSoOriginal("Windows 11");
		computer.setStatus(Status.PRA_USO);
		computer.setEntryDate(LocalDate.now());
		computer.setDepartureDate(null);
		computer = computerRepository.save(computer);
		assertNotNull(computer.getId());
	}
	
	@Test
	public void newComputerPatrimonyUniqueNokTest() {
		Computer computer = new Computer();
		computer.setPatrimony("NTK19188");
		computer.setSn("14719733422");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		computer.setStatus(Status.PRA_USO);
		computer.setEntryDate(LocalDate.now());
		computer.setDepartureDate(null);
        assertThrows(DataIntegrityViolationException.class, () -> {
            computerRepository.save(computer);
        });
	}
	
	@Test
	public void newComputerSnUniqueNokTest() {
		Computer computer = new Computer();
		computer.setPatrimony("NTK19190");
		computer.setSn("14719733488");
		computer.setSector("IT");
		computer.setModel("Inspiron 14R 5437");
		computer.setBrand("Dell");
		computer.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer.setSoOriginal("Windows 10");
		computer.setStatus(Status.PRA_USO);
		computer.setEntryDate(LocalDate.now());
		computer.setDepartureDate(null);
        assertThrows(DataIntegrityViolationException.class, () -> {
            computerRepository.save(computer);
        });
	}
}