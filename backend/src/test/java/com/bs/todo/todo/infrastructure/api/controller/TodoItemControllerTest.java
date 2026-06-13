package com.bs.todo.todo.infrastructure.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TodoItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateTodoItemAndReturn201() throws Exception {
        mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "description": "Öl wechsel",
                            "completionDate": "20.06.2026"
                        }
                        """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.description").value("Öl wechsel"))
                .andExpect(jsonPath("$.completionDate").value("20.06.2026"));
    }

    @Test
    void shouldReturn400WhenDescriptionIsBlank() throws Exception {
        mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "description": "",
                            "completionDate": "20.06.2026"
                        }
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenDateFormatIsInvalid() throws Exception {
        mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "description": "Öl wechsel",
                            "completionDate": "2026-06-20"
                        }
                        """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnAllTodoItemsAndReturn200() throws Exception {
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "description": "Öl wechsel",
                            "completionDate": "20.06.2026"
                        }
                        """));

        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void shouldReturn204WhenDeletingExistingEntry() throws Exception {
        String response = mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "description": "Öl wechsel",
                            "completionDate": "20.06.2026"
                        }
                        """))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = new ObjectMapper()
                .readTree(response)
                .get("id")
                .asText();

        mockMvc.perform(delete("/api/todos/" + id))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldReturn404WhenDeletingUnknownId() throws Exception {
        mockMvc.perform(delete("/api/todos/00000000-0000-0000-0000-000000000000"))
                .andExpect(status().isNotFound());
    }
}