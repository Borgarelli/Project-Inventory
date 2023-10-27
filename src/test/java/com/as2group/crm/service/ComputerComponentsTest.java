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

import com.as2group.crm.enumeration.ComponentsStatus;
import com.as2group.crm.enumeration.ComputerStatus;
import com.as2group.crm.model.Components;
import com.as2group.crm.model.Computer;
import com.as2group.crm.model.ComputerComponent;

import com.as2group.crm.repository.ComponentsRepository;
import com.as2group.crm.repository.ComputerComponentRepository;
import com.as2group.crm.repository.ComputerRepository;

@SpringBootTest
public class ComputerComponentsTest {
    
    @Autowired
    ComputerComponentService computerComponentService;

    @MockBean
    ComputerComponentRepository computerComponentRepository;

    @MockBean
    ComputerRepository computerRepository;

    @MockBean
    ComponentsRepository componentsRepository;


    @BeforeEach
    public void setUp(){

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

        Computer computerInactive = new Computer();
		computerInactive.setId(2L);
		computerInactive.setPatrimony("NTK19188");
		computerInactive.setSn("14719733488");
		computerInactive.setSector("IT");
		computerInactive.setModel("Inspiron 14R 5437");
		computerInactive.setBrand("Dell");
		computerInactive.setSoCurrent("Ubuntu 22.04.2 LTS");
		computerInactive.setSoOriginal("Windows 10");
		computerInactive.setStatus(ComputerStatus.INATIVO);
		computerInactive.setEntryDate(LocalDate.now());

        Computer computer2 = new Computer();
		computer2.setId(3L);
		computer2.setPatrimony("NTK19188");
		computer2.setSn("14719733488");
		computer2.setSector("IT");
		computer2.setModel("Inspiron 14R 5437");
		computer2.setBrand("Dell");
		computer2.setSoCurrent("Ubuntu 22.04.2 LTS");
		computer2.setSoOriginal("Windows 10");
		computer2.setStatus(ComputerStatus.PRA_USO);
		computer2.setEntryDate(LocalDate.now());

        Components componentReadyToUse = new Components();
        componentReadyToUse.setId(1L);
        componentReadyToUse.setPatrimony("NTK191253");
        componentReadyToUse.setSn("14719733453");
        componentReadyToUse.setSpecifications("green");
        componentReadyToUse.setStatus(ComponentsStatus.PRA_USO);

        Components componentInUse = new Components();
        componentInUse.setId(2L);
        componentInUse.setPatrimony("NTK191253");
        componentInUse.setSn("14719733453");
        componentInUse.setSpecifications("green");
        componentInUse.setComputer(computer);
        componentInUse.setStatus(ComponentsStatus.EM_USO);

        Components componentInactive = new Components();
        componentInactive.setId(3L);
        componentInactive.setPatrimony("NTK191253");
        componentInactive.setSn("14719733453");
        componentInactive.setSpecifications("green");
        componentInactive.setStatus(ComponentsStatus.INATIVO);

        ComputerComponent computerComponent = new ComputerComponent();
        computerComponent.setId_comp_compo(1L);
        computerComponent.setComponent(componentInUse);
        computerComponent.setComputer(computer);
        computerComponent.setReceived(LocalDateTime.now());

        ComputerComponent computerComponentDifferent = new ComputerComponent();
        computerComponentDifferent.setId_comp_compo(2L);
        computerComponentDifferent.setComponent(componentInUse);
        computerComponentDifferent.setComputer(computer2);
        computerComponentDifferent.setReceived(LocalDateTime.now());

        Mockito.when(computerComponentRepository.findById(1L)).thenReturn(Optional.of(computerComponent));
        Mockito.when(computerComponentRepository.findById(2L)).thenReturn(Optional.of(computerComponentDifferent));

        Mockito.when(computerRepository.findById(1L)).thenReturn(Optional.of(computer));
        Mockito.when(computerRepository.findById(2L)).thenReturn(Optional.of(computerInactive));
        Mockito.when(computerRepository.findById(3L)).thenReturn(Optional.of(computer2));

        Mockito.when(componentsRepository.findById(1L)).thenReturn(Optional.of(componentReadyToUse));
        Mockito.when(componentsRepository.findById(2L)).thenReturn(Optional.of(componentInUse));
        Mockito.when(componentsRepository.findById(3L)).thenReturn(Optional.of(componentInactive));

        Mockito.when(computerComponentRepository.findByComputer(any())).thenReturn(
				List.of(new ComputerComponent()));
		Mockito.when(computerComponentRepository.findByComponent(any())).thenReturn(
				List.of(new ComputerComponent()));
	    Mockito.when(computerComponentRepository.findByComputerAndComponent(computer, componentInUse))
        .thenReturn(List.of(computerComponent));
    }

    @Test
    public void newComponentOnComputerOkTest() {
        Components component = new Components();
        component.setId(1L);
        component.setPatrimony("NTK191253");
        component.setSn("14719733453");
        component.setSpecifications("green");
        component.setStatus(ComponentsStatus.PRA_USO);

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

        ComputerComponent computerComponent = new ComputerComponent();
        computerComponent.setId_comp_compo(1L);
        computerComponent.setComponent(component);
        computerComponent.setComputer(computer);
        computerComponent.setReceived(LocalDateTime.now());

        Mockito.when(componentsRepository.findById(1L)).thenReturn(Optional.of(component));
        Mockito.when(computerRepository.findById(1L)).thenReturn(Optional.of(computer));

        assertDoesNotThrow(() -> {
            computerComponentService.link(1L, 1L);
        });
    }

    //linkTests
    @Test
    public void linkComponentOnComputerOkTest() {
        assertDoesNotThrow(() -> {
            computerComponentService.link(1L, 1L);
        });
    }

    @Test
    public void linkMoreComponentOkTest() {
        Components component = new Components();
        component.setId(1L);
        component.setPatrimony("NTK191253");
        component.setSn("14719733453");
        component.setSpecifications("green");
        component.setStatus(ComponentsStatus.PRA_USO);

        Mockito.when(componentsRepository.findById(1L)).thenReturn(Optional.of(component));

        assertDoesNotThrow(() -> {
            computerComponentService.link(2L, 1L);
        });
    }
    @Test
    public void linkComponentComputerInactiveNOkTest() {
        assertThrows(ResponseStatusException.class, () -> {
            computerComponentService.link(2L, 1L);
        });
    }

    @Test
    public void linkComponentInactiveComputerNOkTest() {
        assertThrows(ResponseStatusException.class, () -> {
            computerComponentService.link(1L, 3L);
        });
    }

    @Test
    public void linkComponentInUseComputerNOkTest(){
        assertThrows(ResponseStatusException.class, () -> {
            computerComponentService.link(3L, 2L);
        });
    }

    @Test
    public void linkComponentComputerNullNOkTest() {
        assertThrows(ResponseStatusException.class, () -> {
            computerComponentService.link(null, 1L);
        });
    }

    //UnlinkTests
    @Test
    public void unlinkComponentComputerOkTest() {
        assertDoesNotThrow(() -> {
            computerComponentService.unlink(1L, 2L);
        });
    }

    @Test
    public void unlinkComponentComputerDifferentNOkTest() {
        assertThrows(ResponseStatusException.class, () -> {
            computerComponentService.unlink(3L, 2L);
        });
    }

    @Test
    public void unlinkComponentNullComputerNOkTest() {
        assertThrows(ResponseStatusException.class, () -> {
            computerComponentService.unlink(3L, null);
        });
    }



    
}