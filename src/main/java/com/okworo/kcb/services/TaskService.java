package com.okworo.kcb.services;

import com.okworo.kcb.entities.TaskEntity;
import com.okworo.kcb.enums.Status;
import com.okworo.kcb.exceptions.NotFoundException;
import com.okworo.kcb.models.dto.TaskDTO;
import com.okworo.kcb.models.request.TaskModel;
import com.okworo.kcb.repsoitory.ProjectRepository;
import com.okworo.kcb.repsoitory.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

/**
 * Author: Morris.Okworo
 * Date:12/3/2024
 */
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public TaskDTO createTask(TaskModel model) {
        var task = modelMapper.map(model, TaskEntity.class);
        task.setProject(projectRepository.findById(model.getProjectId()).orElseThrow(() ->
                new NotFoundException("Project not found")));
        return modelMapper.map(taskRepository.save(task), TaskDTO.class);
    }

    public Page<TaskDTO> getTasks(UUID projectId, Optional<Status> status, Optional<LocalDate> dueDate, Pageable pageable) {
        Page<TaskEntity> tasks;

        if (status.isPresent() && dueDate.isPresent()) {
            tasks = taskRepository.findByProjectIdAndStatusAndDueDate(projectId, status.get(), dueDate.get(), pageable);
        } else if (status.isPresent()) {
            tasks = taskRepository.findByProjectIdAndStatus(projectId, status.get(), pageable);
        } else if (dueDate.isPresent()) {
            tasks = taskRepository.findByProjectIdAndDueDate(projectId, dueDate.get(), pageable);
        } else {
            tasks = taskRepository.findByProjectId(projectId, pageable);
        }

        return tasks.map(taskEntity -> modelMapper.map(taskEntity, TaskDTO.class));
    }


    public TaskDTO updateTask(TaskModel model) {
        taskRepository.findById(model.getId()).orElseThrow(() ->
                new NotFoundException("Task not found"));
        var task = modelMapper.map(model, TaskEntity.class);
        return modelMapper.map(taskRepository.save(task), TaskDTO.class);

    }

    public void deleteTask(UUID taskId) {
        var task = taskRepository.findById(taskId).orElseThrow(() ->
                new NotFoundException("Task not found"));
        taskRepository.delete(task);
    }
}
