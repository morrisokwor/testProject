package com.okworo.kcb.repsoitory;

import com.okworo.kcb.entities.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Author: Morris.Okworo
 * Date:12/3/2024
 */
public interface ProjectRepository extends JpaRepository<ProjectEntity, UUID> {
}
