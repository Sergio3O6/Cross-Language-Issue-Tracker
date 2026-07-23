package com.example.issuetracker.controller;

import com.example.issuetracker.exception.ResourceNotFoundException;
import com.example.issuetracker.model.Issue;
import com.example.issuetracker.model.Priority;
import com.example.issuetracker.model.Status;
import com.example.issuetracker.service.IssueService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/** Web-layer tests: controller with a mocked service, no database. */
@WebMvcTest(IssueController.class)
class IssueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IssueService service;

    @Test
    void getIssues_returnsList() throws Exception {
        Issue issue = new Issue("Login broken", "500 error", Status.OPEN, Priority.HIGH, "alice");
        issue.setId(1L);
        when(service.findAll(null)).thenReturn(List.of(issue));

        mockMvc.perform(get("/api/issues"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Login broken"))
                .andExpect(jsonPath("$[0].status").value("OPEN"));
    }

    @Test
    void getIssue_whenMissing_returns404() throws Exception {
        when(service.findById(eq(99L))).thenThrow(ResourceNotFoundException.issue(99L));

        mockMvc.perform(get("/api/issues/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Issue not found with id 99"));
    }

    @Test
    void createIssue_withValidBody_returns201() throws Exception {
        Issue saved = new Issue("New bug", "desc", Status.OPEN, Priority.MEDIUM, "bob");
        saved.setId(5L);
        when(service.create(any())).thenReturn(saved);

        Map<String, Object> body = Map.of(
                "title", "New bug",
                "description", "desc",
                "status", "OPEN",
                "priority", "MEDIUM",
                "assignee", "bob");

        mockMvc.perform(post("/api/issues")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5));
    }
}
