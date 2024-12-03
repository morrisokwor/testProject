package com.okworo.kcb.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.okworo.kcb.enums.Status;
import com.okworo.kcb.models.dto.TaskDTO;
import com.okworo.kcb.services.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.UUID;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author: Morris.Okworo
 * Date:12/3/2024
 */

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    TaskService taskService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void verifyEmailAddress() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("COUNTRY_CODE", "KE");


        var builder = MockMvcRequestBuilders.post("/verify/email")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .requestAttr("email_address", "k.kitinya@gmail.com")
                .headers(headers);
//        MockHttpServletResponse response= mockMvc.perform(builder).andExpect(status().)
    }

//    @Test
//    void createTask() throws Exception {
//
//        // Creating a sample TaskDTO object in JSON format
//        String taskJson = "{\n" +
//                "  \"title\": \"Task 1\",\n" +
//                "  \"description\": \"Description for task 1\",\n" +
//                "  \"status\": \"TO_DO\",\n" +
//                "  \"dueDate\": \"2024-12-15\"\n" +
//                "}";
//
//        String projectId = "a76e423a-e7d1-402b-852b-80b3c66cbd71";
//
//        var builder = MockMvcRequestBuilders.post("/projects/{projectId}/tasks", projectId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(taskJson);
//
//        var response = mockMvc.perform(builder)
//                .andExpect(status().isOk())
//                .andReturn().getResponse();
//    }

//    @Test
//    void getTasks() throws Exception {
//
//        // Project ID (Assuming UUID)
//        String projectId = "a76e423a-e7d1-402b-852b-80b3c66cbd71";
//
//        var builder = MockMvcRequestBuilders.get("/projects/{projectId}/tasks", projectId)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8");
//
//        // Perform the request and assert the status
//        var response = mockMvc.perform(builder)
//                .andExpect(status().isOk())  // Assuming the endpoint returns HTTP 200 OK
//                .andExpect(jsonPath("$").isArray())  // Ensure the response body is an array
//                .andExpect(jsonPath("$[0].title").value("Task 1"))  // Check for the task title
//                .andExpect(jsonPath("$[0].status").value("TO_DO"))  // Check for the task status
//                .andReturn().getResponse();
//    }
}