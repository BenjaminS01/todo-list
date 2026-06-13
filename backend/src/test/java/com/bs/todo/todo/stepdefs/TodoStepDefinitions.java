package com.bs.todo.todo.stepdefs;

import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class TodoStepDefinitions {
    @Autowired
    private MockMvc mockMvc;

    private ResultActions result;

    @When("I send a POST request with body:")
    public void sendPostRequest(String body) throws Exception {
        result = mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body));
    }

    @Then("I receive status {int}")
    public void checkStatus(int status) throws Exception {
        result.andExpect(status().is(status));
    }
}
