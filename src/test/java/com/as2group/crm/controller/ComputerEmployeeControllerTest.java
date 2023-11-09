package com.as2group.crm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class ComputerEmployeeControllerTest {
 
    @Autowired
    private MockMvc mvc;

    @Test
    public void linkComputerOnEmployeeOkTest() throws Exception {
        mvc.perform(post("/api/computers/{computerId}/employees/{employeeId}", 1L, 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id_comp_empl").exists());
    }

    @Test
    public void linkComputerInUseNOkTest() throws Exception {
        mvc.perform(post("/api/computers/{computerId}/employees/{employeeId}", 2L, 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void linkComputerOnEmployeeInactiveNOkTest() throws Exception {
        mvc.perform(post("/api/computers/{computerId}/employees/{employeeId}", 1L, 2L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void linkComputerInactiveOnEmployeeNOkTest() throws Exception {
        mvc.perform(post("/api/computers/{computerId}/employees/{employeeId}", 3L,1L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void unlinkComputerOkTest() throws Exception {
        mvc.perform(delete("/api/computers/{computerId}/employees/{employeeId}", 2L,1L) 
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void unlinkComputerDifferentNOkTest() throws Exception {
        mvc.perform(delete("/api/computers/{computerId}/employees/{employeeId}", 2L, 3L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void findByIdOkTest() throws Exception {
        mvc.perform(get("/api/computers/{computerEmployeeId}/employees", 1L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isAccepted())
        .andExpect(jsonPath("$.id_comp_empl").value(1L));
    }

    @Test
    public void computerEmployeeNotFoundNOkTest() throws Exception {
        mvc.perform(get("/api/computers/{computerEmployeeId}/employees", 5L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
    }

    @Test
    public void historicComputerOkTest() throws Exception {
        mvc.perform(get("/api/computers/{computerId}/historic", 2L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void historicComputerNotFound() throws Exception {
        mvc.perform(get("/api/computers/{computerId}/historic", 5L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
    }

    @Test
    public void historicEmployeeOkTest() throws Exception {
        mvc.perform(get("/api/employees/{employeeId}/historic", 1L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void historicEmployeeNotFoundNOkTest() throws Exception {
        mvc.perform(get("/api/employees/{employeeId}/historic", 5L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
    }

}
