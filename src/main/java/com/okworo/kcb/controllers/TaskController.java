package com.okworo.kcb.controllers;

import com.okworo.kcb.models.dto.TaskDTO;
import com.okworo.kcb.models.request.TaskModel;
import com.okworo.kcb.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Author: Morris.Okworo
 * Date:12/3/2024
 */
@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDTO> updateTask(@Validated @RequestBody TaskModel model, @PathVariable UUID taskId) {
        model.setId(taskId);
        return ResponseEntity.ok(taskService.updateTask(model));
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }


}
