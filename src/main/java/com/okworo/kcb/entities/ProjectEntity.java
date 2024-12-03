package com.okworo.kcb.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

/**
 * Author: Morris.Okworo
 * Date:12/3/2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;
    @NotBlank
    @Column(nullable = false)
    private String name;

    private String description;
}
