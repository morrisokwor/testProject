package com.okworo.kcb.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.okworo.kcb.entities.ProjectEntity;
import com.okworo.kcb.enums.Status;
import com.okworo.kcb.exceptions.CustomExceptionResponseAdvice;
import com.okworo.kcb.models.dto.ProjectDTO;
import com.okworo.kcb.models.dto.TaskDTO;
import com.okworo.kcb.models.request.TaskModel;
import com.okworo.kcb.repsoitory.ProjectRepository;
import com.okworo.kcb.services.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author: Morris.Okworo
 * Date:12/3/2024
 */

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {
    @Mock
    private MockMvc mockMvc;

    @Mock
    private TaskService taskService;

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectController projectController;

    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(projectController)
                .setControllerAdvice(new CustomExceptionResponseAdvice())
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Transactional
    @Test
    public void shouldAddTaskSuccessfully() throws Exception {


        UUID projectId = UUID.randomUUID();
        TaskModel taskModel = TaskModel.builder()
                .title("Test Task")
                .description("Task Description")
                .status(Status.TO_DO)
                .dueDate(LocalDate.now().plusDays(1))
                .projectId(projectId)
                .build();

        var taskDTO = TaskDTO.builder()
                .title("Test Task")
                .description("Task Description")
                .status(Status.TO_DO)
                .dueDate(LocalDate.now().plusDays(1))
                .project(ProjectDTO.builder()
                        .id(projectId)
                        .name("Test Project")
                        .build())
                .build();

        var project = new ProjectEntity();
        project.setId(projectId);

        when(projectRepository.findById(any(UUID.class))).thenReturn(Optional.of(project));
        when(taskService.createTask(any(TaskModel.class))).thenReturn(taskDTO);

        mockMvc.perform(post("/projects/{projectId}/tasks", projectId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(taskModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Task"))
        ;
    }

}