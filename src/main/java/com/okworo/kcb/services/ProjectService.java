package com.okworo.kcb.services;

import com.okworo.kcb.entities.ProjectEntity;
import com.okworo.kcb.enums.Status;
import com.okworo.kcb.models.dto.ProjectDTO;
import com.okworo.kcb.models.request.ProjectModel;
import com.okworo.kcb.repsoitory.ProjectRepository;
import com.okworo.kcb.repsoitory.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Author: Morris.Okworo
 * Date:12/3/2024
 */
@Service
@RequiredArgsConstructor
public class ProjectService {


    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    public ProjectDTO createProject(ProjectModel model) {
        var project = modelMapper.map(model, ProjectEntity.class);
        return modelMapper.map(projectRepository.save(project), ProjectDTO.class);
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectEntity -> modelMapper.map(projectEntity, ProjectDTO.class))
                .collect(Collectors.toList());
    }


    public ProjectDTO getProjectById(UUID projectId) {
        var project = projectRepository.findById(projectId).orElseThrow(() -> new RuntimeException("Project not found"));
        return modelMapper.map(project, ProjectDTO.class);
    }

    public List<Map<String, Object>> getProjectSummary() {
        List<ProjectEntity> projects = projectRepository.findAll();

        return projects.stream().map(project -> {
            Map<Status, Long> taskCountByStatus = taskRepository.findByProjectId(project.getId())
                    .stream()
                    .collect(Collectors.groupingBy(taskEntity -> taskEntity.getStatus(), Collectors.counting()));

            // Create a Map for each project
            Map<String, Object> projectSummary = new HashMap<>();
            projectSummary.put("projectId", project.getId());
            projectSummary.put("projectName", project.getName());
            projectSummary.put("taskCountByStatus", taskCountByStatus);

            return projectSummary;
        }).collect(Collectors.toList());
    }


}
