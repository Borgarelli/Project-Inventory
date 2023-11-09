package com.as2group.crm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class ComputerEmployeeControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void linkComputerOnEmployeeOkTest() throws Exception {
        mvc.perform(post("/api/computers/{computerId}/employees/{employeeId}", 1L,1l)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated());
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
    public void linkComputerOnInactiveEmployeeNOkTest() throws Exception {
        mvc.perform(post("/api/computers/{computerId}/employees/{employeeId}", 1L,2L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }
}
