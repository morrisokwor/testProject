//package com.okworo.kcb.controllers;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.okworo.kcb.enums.Status;
//import com.okworo.kcb.exceptions.NotFoundException;
//import com.okworo.kcb.models.dto.TaskDTO;
//import com.okworo.kcb.models.request.TaskModel;
//import com.okworo.kcb.services.TaskService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.time.LocalDate;
//import java.util.UUID;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * Author: Morris.Okworo
// * Date:12/3/2024
// */
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class TaskControllerTest {
////
////    private MockMvc mockMvc;
////
////    @Mock
////    private TaskService taskService;
////
////    @InjectMocks
////    private TaskController taskController;
////
////    private ObjectMapper objectMapper = new ObjectMapper();
////
////    @BeforeEach
////    void setup() {
////        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
////    }
////
////    @Test
////    void testCreateTask() throws Exception {
////        TaskModel taskModel = TaskModel.builder()
////                .id(UUID.randomUUID())
////                .projectId(UUID.randomUUID())
////                .title("Test Task")
////                .description("Test Task Description")
////                .dueDate(LocalDate.now().plusDays(1))
////                .status(Status.TO_DO)
////                .build();
////
////        TaskDTO taskDTO = TaskDTO.builder()
////                .id(UUID.randomUUID())
////                .description("Description")
////                .status(Status.TO_DO)
////                .dueDate(LocalDate.now().plusDays(1))
////                .build();
////
////
////        when(taskService.createTask(Mockito.any(TaskModel.class))).thenReturn(taskDTO);
////
////        mockMvc.perform(post("/projects/{projectId}/tasks", taskModel.getProjectId())
////                        .contentType("application/json")
////                        .content(objectMapper.writeValueAsString(taskModel)))
////                .andExpect(status().isOk())
////                .andExpect((ResultMatcher) jsonPath("$.title").value("Test Task"))
////                .andExpect((ResultMatcher) jsonPath("$.status").value(Status.TO_DO.toString()));
////    }
////
////    @Test
////    void testCreateTaskProjectNotFound() throws Exception {
////        TaskModel taskModel = TaskModel.builder()
////                .id(UUID.randomUUID())
////                .projectId(UUID.randomUUID())
////                .title("Test Task")
////                .description("Test Task Description")
////                .dueDate(LocalDate.now().plusDays(1))
////                .status(Status.TO_DO)
////                .build();
////        when(taskService.createTask(Mockito.any(TaskModel.class))).thenThrow(new NotFoundException("Project not found"));
////
////        mockMvc.perform(post("/projects/{projectId}/tasks", taskModel.getProjectId())
////                        .contentType("application/json")
////                        .content(objectMapper.writeValueAsString(taskModel)))
////                .andExpect(status().isNotFound())
////                .andExpect((ResultMatcher) jsonPath("$.message").value("Project not found"));
////    }
//}