package com.as2group.crm.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.as2group.crm.enumeration.ComputerStatus;
import com.as2group.crm.model.Computer;
import com.as2group.crm.repository.ComputerRepository;

@SpringBootTest
public class ComputerTest {
    
    @Autowired
    private ComputerService computerService;

    @MockBean
    private ComputerRepository computerRepository;

    @BeforeEach
    public void setUp(){
        Computer computer = new Computer();
        computer.setId(1L);
        computer.setStatus(ComputerStatus.PRA_USO);
        computer.setPatrimony("NTK191220");
        computer.setSn("14719733426");
        computer.setModel("Inspiron 14R 5437");
        computer.setBrand("Dell");
        computer.setSoCurrent("Ubuntu 22.04.2 LTS");
        computer.setSoOriginal("Windows 10");
        computer.setEntryDate(LocalDate.now());
        List<Computer> computers = new ArrayList<>();
        computers.add(computer);
        Optional<Computer> computerOp = Optional.of(computer);

        Mockito.when(computerRepository.findById(1L)).thenReturn(computerOp);

        Mockito.when(computerRepository.save(any())).thenReturn(computer);

        Mockito.when(computerRepository.findAll()).thenReturn(computers);
    }

    @Test
    public void createComputerTestOk() {
        Computer computer = new Computer();
        computer.setId(1L);
        computer.setStatus(ComputerStatus.PRA_USO);
        computer.setPatrimony("NTK191220");
        computer.setSn("14719733426");
        computer.setModel("Inspiron 14R 5437");
        computer.setBrand("Dell");
        computer.setSoCurrent("Ubuntu 22.04.2 LTS");
        computer.setSoOriginal("Windows 10");
        computer.setEntryDate(LocalDate.now());
        computer.setDepartureDate(null);
        computer.setModificationDate(null);
        computer.setComputerComponents(null);
        assertDoesNotThrow(() -> {
            computerService.create(computer);
        });
    }

    @Test
    public void createComputerPatrimonyNullNOkTest() {
        Computer computer = new Computer();
        computer.setId(1L);
        computer.setStatus(ComputerStatus.PRA_USO);
        computer.setPatrimony("NTK191220");
        computer.setSn("");
        computer.setModel("Inspiron 14R 5437");
        computer.setBrand("Dell");
        computer.setSoCurrent("Ubuntu 22.04.2 LTS");
        computer.setSoOriginal("Windows 10");
        computer.setEntryDate(LocalDate.now());
        computer.setDepartureDate(null);
        computer.setModificationDate(null);
        computer.setComputerComponents(null);
        assertThrows(IllegalArgumentException.class, () -> {
            computerService.create(computer);
        });
    }

        @Test
    public void createComputerSnNullNOkTest() {
        Computer computer = new Computer();
        computer.setId(1L);
        computer.setStatus(ComputerStatus.PRA_USO);
        computer.setPatrimony("");
        computer.setSn("14719733426");
        computer.setModel("Inspiron 14R 5437");
        computer.setBrand("Dell");
        computer.setSoCurrent("Ubuntu 22.04.2 LTS");
        computer.setSoOriginal("Windows 10");
        computer.setEntryDate(LocalDate.now());
        computer.setDepartureDate(null);
        computer.setModificationDate(null);
        computer.setComputerComponents(null);
        assertThrows(IllegalArgumentException.class, () -> {
            computerService.create(computer);
        });
    }

    @Test
    public void findPatrimonyByIdOkTest() {
        assertEquals("NTK191220", computerService.show(1L).getPatrimony());
    }

    @Test
    public void findSnByIdOkTest(){
        assertEquals("14719733426", computerService.show(1L).getSn());
    }
}
