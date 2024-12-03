package com.okworo.kcb.models.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

/**
 * Author: Morris.Okworo
 * Date:12/3/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectModel {
    private UUID id;
    @NotBlank(message = "Name Cannot be blank")
    @NotEmpty(message = "Name missing")
    private String name;
    private String description;
}
