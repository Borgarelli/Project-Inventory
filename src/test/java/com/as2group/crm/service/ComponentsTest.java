package com.as2group.crm.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

import com.as2group.crm.enumeration.ComponentsStatus;
import com.as2group.crm.enumeration.ComputerStatus;
import com.as2group.crm.model.Components;
import com.as2group.crm.model.Computer;
import com.as2group.crm.repository.ComponentsRepository;
import com.as2group.crm.repository.ComputerRepository;

@SpringBootTest
public class ComponentsTest {

    @Autowired
    ComponentsService componentsService;

    @MockBean
    ComponentsRepository componentsRepository;

    @MockBean
    ComputerRepository computerRepository;
    
    @BeforeEach
    public void setUp() {
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

        List<Components> components = new ArrayList<>();
        components.add(componentReadyToUse);

        Mockito.when(computerRepository.findById(1L)).thenReturn(Optional.of(computer));

        Mockito.when(componentsRepository.findById(1L)).thenReturn(Optional.of(componentReadyToUse));
        Mockito.when(componentsRepository.findById(2L)).thenReturn(Optional.of(componentInUse));
        Mockito.when(componentsRepository.findById(3L)).thenReturn(Optional.of(componentInactive));
        
        Mockito.when(componentsRepository.findByPatrimony("NTK191253")).thenReturn(Optional.of(componentReadyToUse));
        Mockito.when(componentsRepository.findBySn("14719733453")).thenReturn(Optional.of(componentReadyToUse));

        Mockito.when(componentsRepository.findAllByStatus(ComponentsStatus.PRA_USO)).thenReturn(Collections.singletonList(componentReadyToUse));
        Mockito.when(componentsRepository.findAllByStatus(ComponentsStatus.EM_USO)).thenReturn(Collections.singletonList(componentInUse));
        Mockito.when(componentsRepository.findAllByStatus(ComponentsStatus.INATIVO)).thenReturn(Collections.singletonList(componentInactive));
        Mockito.when(componentsRepository.findAll()).thenReturn(components);
    }

    @Test
    public void newComponentOkTest() {
        Components component = new Components();
        component.setPatrimony("NTK191260");
        component.setSn("14719733460");
        component.setSpecifications("green");

        assertDoesNotThrow(() -> {
            componentsService.create(component);
        } );
    }

    @Test
    public void newComponentPatrimonyUniqueNOkTest() {
        Components component = new Components();
        component.setPatrimony("NTK191253");
        component.setSn("14719733460");
        component.setSpecifications("green");

        assertThrows(ResponseStatusException.class, () -> {
            componentsService.create(component);
        });
    }

    @Test
    public void newComponentPatrimonyNullNOkTest() {
        Components component = new Components();
        component.setPatrimony(null);
        component.setSn("14719733460");
        component.setSpecifications("green");

        assertThrows(IllegalArgumentException.class, () -> {
            componentsService.create(component);
        });
    }

    @Test
    public void newComponentPatrimonyEmptyNOkTest() {
        Components component = new Components();
        component.setPatrimony("");
        component.setSn("14719733460");
        component.setSpecifications("green");

        assertThrows(IllegalArgumentException.class, () -> {
            componentsService.create(component);
        });
    }

    @Test
    public void newComponentSnUniqueNOkTest() {
        Components component = new Components();
        component.setPatrimony("NTK191260");
        component.setSn("14719733453");
        component.setSpecifications("green");

        assertThrows(ResponseStatusException.class, () -> {
            componentsService.create(component);
        });
    }

    @Test
    public void newComponentSnNullNOkTest() {
        Components component = new Components();
        component.setPatrimony("NTK191260");
        component.setSn(null);
        component.setSpecifications("green");

        assertThrows(IllegalArgumentException.class, () -> {
            componentsService.create(component);
        });
    }

    @Test
    public void newComponentSnEmptyNOkTest() {
        Components component = new Components();
        component.setPatrimony("NTK191260");
        component.setSn("");
        component.setSpecifications("green");

        assertThrows(IllegalArgumentException.class, () -> {
            componentsService.create(component);
        });
    }


}
