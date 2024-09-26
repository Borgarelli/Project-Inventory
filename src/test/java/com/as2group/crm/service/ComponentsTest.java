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
		computer.setStatus(Status.PRA_USO);
		computer.setEntryDate(LocalDate.now());

        Components componentReadyToUse = new Components();
        componentReadyToUse.setId(1L);
        componentReadyToUse.setPatrimony("NTK191253");
        componentReadyToUse.setSn("14719733453");
        componentReadyToUse.setSpecifications("green");
        componentReadyToUse.setStatus(Status.PRA_USO);

        Components componentInUse = new Components();
        componentInUse.setId(2L);
        componentInUse.setPatrimony("NTK191253");
        componentInUse.setSn("14719733453");
        componentInUse.setSpecifications("green");
        componentInUse.setComputer(computer);
        componentInUse.setStatus(Status.EM_USO);

        Components componentInactive = new Components();
        componentInactive.setId(3L);
        componentInactive.setPatrimony("NTK191253");
        componentInactive.setSn("14719733453");
        componentInactive.setSpecifications("green");
        componentInactive.setStatus(Status.INATIVO);

        List<Components> components = new ArrayList<>();
        components.add(componentReadyToUse);

        Mockito.when(computerRepository.findById(1L)).thenReturn(Optional.of(computer));

        Mockito.when(componentsRepository.findById(1L)).thenReturn(Optional.of(componentReadyToUse));
        Mockito.when(componentsRepository.findById(2L)).thenReturn(Optional.of(componentInUse));
        Mockito.when(componentsRepository.findById(3L)).thenReturn(Optional.of(componentInactive));
        
        Mockito.when(componentsRepository.findByPatrimony("NTK191253")).thenReturn(Optional.of(componentReadyToUse));
        Mockito.when(componentsRepository.findBySn("14719733453")).thenReturn(Optional.of(componentReadyToUse));

        Mockito.when(componentsRepository.findAllByStatus(Status.PRA_USO)).thenReturn(Collections.singletonList(componentReadyToUse));
        Mockito.when(componentsRepository.findAllByStatus(Status.EM_USO)).thenReturn(Collections.singletonList(componentInUse));
        Mockito.when(componentsRepository.findAllByStatus(Status.INATIVO)).thenReturn(Collections.singletonList(componentInactive));
        Mockito.when(componentsRepository.findByStatusActivate()).thenReturn(components);

        Mockito.when(componentsRepository.findAll()).thenReturn(Collections.singletonList(componentReadyToUse));
    }

    @Test
    public void newComponentOkTest() {
        Components component = new Components();
        component.setPatrimony("NTK191260");
        component.setSn("14719733460");
        component.setSpecifications("green");

        Mockito.when(componentsRepository.save(any())).thenReturn(component);

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


    //Edit tests
    @Test
    public void editComponentOkTest() {
        Components component = new Components();
        component.setPatrimony("NTK191260");
        component.setSn("14719733460");
        component.setSpecifications("red");
        
        assertDoesNotThrow(() -> {
            componentsService.edit(1L, component);
        });
    }

    @Test
    public void editComponentNullNOkTest() {
        Components component = new Components();
        component.setPatrimony("NTK191260");
        component.setSn("14719733453");
        component.setSpecifications("green");

        assertThrows(ResponseStatusException.class, () -> {
            componentsService.edit(null, component);
        });
    }

    @Test
    public void editComponentInvalidNOkTest() {
        Components component = new Components();
        component.setPatrimony("NTK191260");
        component.setSn("14719733460");
        component.setSpecifications("green");

        assertThrows(ResponseStatusException.class, () -> {
            componentsService.edit(4L, component);
        });
    }

    @Test
    public void editComponentPatrimonyUniqueNOkTest() {
        Components component = new Components();
        component.setPatrimony("NTK191253");
        component.setSn("14719733460");
        component.setSpecifications("green");

        assertThrows(ResponseStatusException.class, () -> {
            componentsService.edit(1L, component);
        });
    }

    @Test
    public void editComponentPatrimonyNullNOkTest() {
        Components component = new Components();
        component.setPatrimony(null);
        component.setSn("14719733460");
        component.setSpecifications("green");

        assertThrows(IllegalArgumentException.class, () -> {
            componentsService.edit(1L, component);
        });
    }

    @Test
    public void editComponentPatrimonyEmptyNOkTest() {
        Components component = new Components();
        component.setPatrimony("");
        component.setSn("14719733460");
        component.setSpecifications("green");

        assertThrows(IllegalArgumentException.class, () -> {
            componentsService.edit(1L, component);
        });
    }

    @Test
    public void editComponentSnUniqueNOkTest() {
        Components component = new Components();
        component.setPatrimony("NTK191260");
        component.setSn("14719733453");
        component.setSpecifications("green");

        assertThrows(ResponseStatusException.class, () -> {
            componentsService.edit(1L, component);
        });
    }

    @Test
    public void editComponentSnNullNOkTest() {
        Components component = new Components();
        component.setPatrimony("NTK191260");
        component.setSn(null);
        component.setSpecifications("green");

        assertThrows(IllegalArgumentException.class, () -> {
            componentsService.edit(1L, component);
        });
    }

    @Test
    public void editComponentsSnEmptyNOkTest() {
        Components component = new Components();
        component.setPatrimony("NTK191260");
        component.setSn("");
        component.setSpecifications("green");

        assertThrows(IllegalArgumentException.class, () -> {
            componentsService.edit(1L, component);
        });
    }

    //Inactivate tests
    @Test
    public void inactivateComponentOkTest() {
        assertDoesNotThrow(() -> {
            componentsService.inactivate(1L);
        });
    }

    @Test
    public void inactivateComponentInvalidNOkTest() {
        assertThrows(ResponseStatusException.class, () -> {
            componentsService.inactivate(4L);
        });
    }

    @Test
    public void inactivateComponentNullNOkTest() {
        assertThrows(ResponseStatusException.class, () -> {
            componentsService.inactivate(null);
        });
    }

    @Test
    public void inactivateComponentInUseNOkTest() {
        assertThrows(ResponseStatusException.class, () -> {
            componentsService.inactivate(2L);
        });
    }

    @Test
    public void inactivateComponentInactiveNOkTest() {
        assertThrows(ResponseStatusException.class, () -> {
            componentsService.inactivate(3L);
        });
    }

    //ActivateTests
    @Test
    public void activateComponentOkTest() {
        assertDoesNotThrow(() -> {
            componentsService.activate(3L);
        });
    }

    @Test
    public void activateComponentActiveNOkTest() {
        assertThrows(ResponseStatusException.class, () -> {
            componentsService.activate(1L);
        });
    }

    @Test
    public void activateComponentInvalidNOkTest() {
            assertThrows(ResponseStatusException.class, () -> {
            componentsService.activate(4L);
        });
    }

    @Test
    public void activateComponentNullNOkTest() {
            assertThrows(ResponseStatusException.class, () -> {
            componentsService.activate(null);
        });
    }

    //Get tests
    @Test
    public void getAllComponentsOkTest() {
        assertEquals(1, componentsService.list().size());
    }

    @Test
    public void findByIdOkTest() {
        assertDoesNotThrow(() -> {
            componentsService.show(1L);
        });
    }

    @Test
    public void findByIdNOkTest() {
        assertThrows(ResponseStatusException.class, () -> {
            componentsService.show(4L);
        });
    }

    @Test
    public void findPatrimonyOkTest(){
        assertDoesNotThrow(() -> {
            componentsService.show("NTK191253");
        });
    }

    @Test
    public void findPatrimonyNOkTest() {
        assertThrows(ResponseStatusException.class, () -> {
            componentsService.show("NTK191257");
        });
    }

    @Test
    public void findPatrimonyInMockOkTest() {
        assertEquals("NTK191253", componentsService.show(1L).getPatrimony());
    }

    @Test
    public void findByStatusOkTest() {
        assertEquals(1, componentsService.listByStatus(Status.PRA_USO).size());
    }

    @Test
    public void stockOkTest() {
        assertEquals(1, componentsService.stock().size());
    }
}
