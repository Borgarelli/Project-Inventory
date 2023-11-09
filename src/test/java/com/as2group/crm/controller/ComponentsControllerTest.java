package com.as2group.crm.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
public class ComponentsControllerTest {
    
    @Autowired
    private MockMvc mvc;

    @Test
    public void getAllComponentsOkTest() throws Exception {
        mvc.perform(get("/api/components")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void getByStatusOkTest() throws Exception {
        mvc.perform(get("/api/components/status/{status}", "INATIVO")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getByIdOkTest() throws Exception {
        mvc.perform(get("/api/components/{id}", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void ComponentNotFoundNOkTest() throws Exception {
        mvc.perform(get("/api/components/{id}", 5L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
    }

    @Test
    public void getByPatrimonyOkTest() throws Exception {
        mvc.perform(get("/api/components/patrimony/{patrimony}", "NTK191253")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    public void patrimonyNotFoundNOkTest() throws Exception {
        mvc.perform(get("/api/components/patrimony/{patrimony}", "NTK191289")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotFound());
    }

    @Test
    public void stockOkTest() throws Exception {
        mvc.perform(get("/api/components/stock")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void createNewComponentOkTest() throws Exception {
        mvc.perform(post("/api/components")
        .content("{\"patrimony\":\"NTK191289\",\"sn\":\"14719733489\",\"specifications\":\"blue\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void createNewComponentPatrimonyUniqueNOkTest() throws Exception {
        mvc.perform(post("/api/components")
        .content("{\"patrimony\":\"NTK191253\",\"sn\":\"14719733489\",\"specifications\":\"blue\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void createNewComponentSnUniqueNOkTest() throws Exception {
        mvc.perform(post("/api/components")
        .content("{\"patrimony\":\"NTK191289\",\"sn\":\"14719733453\",\"specifications\":\"pink\"}")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void inactivateComponentOkTest() throws Exception {
        mvc.perform(delete("/api/components/{id}/inactivate", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void inactivateComponentInUseNOkTest() throws Exception {
        mvc.perform(delete("/api/components/{id}/inactivate", 2L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void inactivateComponentInactiveNOkTest() throws Exception {
        mvc.perform(delete("/api/components/{id}/inactivate", 3L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }

    @Test
    public void activateComponentOkTest() throws Exception {
        mvc.perform(put("/api/components/{id}/activate", 3L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void activateComponentActiveNOkTest() throws Exception {
        mvc.perform(put("/api/components/{id}/activate", 1L)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isBadRequest());
    }
}
