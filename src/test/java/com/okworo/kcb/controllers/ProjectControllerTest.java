package com.okworo.kcb.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.okworo.kcb.exceptions.CustomExceptionResponseAdvice;
import com.okworo.kcb.exceptions.NotFoundException;
import com.okworo.kcb.models.dto.ProjectDTO;
import com.okworo.kcb.models.request.ProjectModel;
import com.okworo.kcb.repsoitory.ProjectRepository;
import com.okworo.kcb.repsoitory.TaskRepository;
import com.okworo.kcb.services.ProjectService;
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

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Author: Morris.Okworo
 * Date:12/3/2024
 */
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerTest {

    @Mock
    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(projectController)
                .setControllerAdvice(new CustomExceptionResponseAdvice())
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();
    }

    @Test
    public void shouldCreateProjectSuccessfully() throws Exception {
        var projectModel = ProjectModel.builder()
                .name("Test Project")
                .description("Test Description")
                .build();

        var dto = ProjectDTO.builder()
                .id(UUID.randomUUID())
                .name("Test Project")
                .description("Test Description")
                .build();

        when(projectService.createProject(any(ProjectModel.class))).thenReturn(dto);

        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(projectModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Project"))
        ;
    }

    @Test
    public void shouldReturnAllProjectsSuccessfully() throws Exception {
        List<ProjectDTO> projects = Arrays.asList(
                new ProjectDTO(UUID.randomUUID(), "Project One", "Description One"),
                new ProjectDTO(UUID.randomUUID(), "Project Two", "Description Two")
        );

        when(projectService.getAllProjects()).thenReturn(projects);

        mockMvc.perform(get("/projects")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void shouldReturnProjectByIdSuccessfully() throws Exception {
        UUID projectId = UUID.randomUUID();
        var projectDTO = new ProjectDTO(projectId, "Project One", "Description One");

        when(projectService.getProjectById(projectId)).thenReturn(projectDTO);

        mockMvc.perform(get("/projects/{projectId}", projectId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNotFoundForInvalidProjectId() throws Exception {
        UUID projectId = UUID.randomUUID();

        when(projectService.getProjectById(projectId)).thenThrow(new NotFoundException("Project not found"));

        mockMvc.perform(get("/projects/{projectId}", projectId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }


}
