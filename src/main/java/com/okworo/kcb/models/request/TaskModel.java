package com.okworo.kcb.models.request;

import com.okworo.kcb.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Author: Morris.Okworo
 * Date:12/3/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskModel {
    private UUID id;
    @NotBlank(message = "Title Cannot be blank")
    @NotEmpty(message = "Title Cannot be empty")
    private String title;
    private String description;
    @NotBlank(message = "Status Cannot be blank")
    @NotEmpty(message = "Status Cannot be empty")
    private Status status;
    @FutureOrPresent(message = "Due date cannot be past")
    private LocalDate dueDate;
    private UUID projectId;
}
