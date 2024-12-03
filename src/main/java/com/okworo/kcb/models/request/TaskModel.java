package com.okworo.kcb.models.request;

import com.okworo.kcb.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String title;
    private String description;
    private Status status;
    private LocalDate dueDate;
    private UUID projectId;
}
