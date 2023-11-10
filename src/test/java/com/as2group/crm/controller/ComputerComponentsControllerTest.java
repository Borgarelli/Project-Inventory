package com.as2group.crm.controller;

import static org.hamcrest.Matchers.hasSize;
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

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback
public class ComputerComponentsControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void linkComponentsOnComputerOkTest() throws Exception {
        mvc.perform(post("/api/computers/{computerId}/components/{componentId}", 1L, 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id_comp_compo").exists());
    }

    @Test
    public void linkOtherComponentOnComputerOkTest() throws Exception {
        mvc.perform(post("/api/computers/{computerId}/components/{componentId}", 2L, 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id_comp_compo").exists());
    }
    
    @Test
    public void linkComponentInUseNOkTest() throws Exception {
        mvc.perform(post("/api/computers/{computerId}/components/{componentId}", 1L, 2L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void linkComponentInactiveOnComputerNOkTest() throws Exception {
        mvc.perform(post("/api/computers/{computerId}/components/{componentId}", 1L, 3L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void unlinkComponentOkTest() throws Exception {
        mvc.perform(delete("/api/computers/{computerId}/components/{componentId}", 2L, 2L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void unlinkComponentDifferentNOkTest() throws Exception {
        mvc.perform(delete("/api/computers/{computerId}/components/{componentId}", 4L, 2L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void historicComponentOkTest() throws Exception {
        mvc.perform(get("/api/components/{componentId}/historic", 2L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
    }
}
