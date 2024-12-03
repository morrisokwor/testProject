package com.okworo.kcb.controllers;

import com.okworo.kcb.enums.Status;
import com.okworo.kcb.models.dto.ProjectDTO;
import com.okworo.kcb.models.dto.TaskDTO;
import com.okworo.kcb.models.request.ProjectModel;
import com.okworo.kcb.models.request.TaskModel;
import com.okworo.kcb.services.ProjectService;
import com.okworo.kcb.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Author: Morris.Okworo
 * Date:12/3/2024
 */
@RestController
@RequestMapping("projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@Validated @RequestBody ProjectModel model) {
        return ResponseEntity.ok(projectService.createProject(model));
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable UUID projectId) {
        return ResponseEntity.ok(projectService.getProjectById(projectId));
    }

    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<TaskDTO> createTask(@Validated @RequestBody TaskModel task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<Page<TaskDTO>> getTasks(
            @PathVariable UUID projectId,
            @RequestParam Optional<Status> status,
            @RequestParam Optional<LocalDate> dueDate,
            Pageable pageable) {
        return ResponseEntity.ok(taskService.getTasks(projectId, status, dueDate, pageable));
    }


    @GetMapping("/summary")
    public ResponseEntity<List<Map<String, Object>>> getProjectSummary() {
        return ResponseEntity.ok(projectService.getProjectSummary());
    }
}
