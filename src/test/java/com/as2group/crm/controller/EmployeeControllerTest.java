package com.as2group.crm.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class EmployeeControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(username = "kauaas@as2group", roles = {"ADM"}) 
    public void newEmployeeOkTest() throws Exception {
        mvc.perform(post("/api/employees")
        .content("{\"name\":\"Kauã Borgarelli\",\"email\":\"kauatavares@as24group.com.br\",\"telephone\":\"1234567890\",\"password\":\"123456\",\"gender\":\"Masculino\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists());
    }

    @Test    
    public void findByIdOktest() throws Exception {
        mvc.perform(get("/api/employees/{id}", 1L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email").value("kauaas2@group"));
    }

    @Test    
    public void findNotFoundEmployeeNOkTest() throws Exception {
        mvc.perform(get("/api/employees/{id}", 5L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
    }

    @Test
    public void findAllOkTest() throws Exception {
        mvc.perform(get("/api/employees")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void findAllInactiveOkTest() throws Exception {
        mvc.perform(get("/api/employees/inactive")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void findAllByNameOkTest() throws Exception {
        mvc.perform(get("/api/employees/name/{name}", "Borgarelli")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(4)));
    }
    
    @Test    
    public void findByEmailOkTest() throws Exception {
        mvc.perform(get("/api/employees/email/{email}", "kauaas2@group")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L));
    }

    @Test    
    public void inactiveEmployeeOkTest() throws Exception {
        mvc.perform(delete("/api/employees/{id}", 3L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test    
    public void inactivateEmployeeWithComputerNOkTest() throws Exception {
        mvc.perform(delete("/api/employees/{id}", 1L) 
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void inactivateEmployeeinactiveNOkTest() throws Exception {
        mvc.perform(delete("/api/employees/{id}", 2L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void inactivateEmployeeNotFoundNOkTest() throws Exception {
        mvc.perform(delete("/api/employees/{id}", 5L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
    }

    @Test
    public void activateEmployeeOkTest() throws Exception {
        mvc.perform(put("/api/employees/{id}/activate", 2L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void activateEmployeeActivateNOkTest() throws Exception {
        mvc.perform(put("/api/employees/{id}/activate", 1L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void activateEmployeeNotFoundNOkTest() throws Exception {
        mvc.perform(put("/api/employees/{id}/activate", 5L)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
    }

    @Test
    public void editEmployeeOkTest() throws Exception {
        mvc.perform(put("/api/employees/{id}", 3L)
        .content("{\"name\":\"Kauã Borgarelli\",\"email\":\"kauatavares@as24group.com.br\",\"telephone\":\"1234567890\",\"gender\":\"Masculino\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void editEmployeeEmailUniqueNOkTest() throws Exception {
        mvc.perform(put("/api/employees/{id}", 1L)
        .content("{\"name\":\"Kauã Borgarelli\",\"email\":\"kaua1as74@group\",\"telephone\":\"1234567890\",\"gender\":\"Masculino\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void editEmployeeNotFoundNOkTest() throws Exception {
        mvc.perform(put("/api/employees/{id}", 5L)
        .content("{\"name\":\"Kauã Borgarelli\",\"email\":\"kauatavares@as2group.com.br\",\"telephone\":\"1234567890\",\"gender\":\"Masculino\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
        
    }

}