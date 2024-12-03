package com.okworo.kcb.repsoitory;

import com.okworo.kcb.entities.TaskEntity;
import com.okworo.kcb.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Author: Morris.Okworo
 * Date:12/3/2024
 */
public interface TaskRepository extends JpaRepository<TaskEntity, UUID> {
    List<TaskEntity> findByProjectId(UUID projectId);

    Page<TaskEntity> findByProjectId(UUID projectId, Pageable pageable);

    Page<TaskEntity> findByProjectIdAndStatus(UUID projectId, Status status, Pageable pageable);

    Page<TaskEntity> findByProjectIdAndDueDate(UUID projectId, LocalDate dueDate, Pageable pageable);

    Page<TaskEntity> findByProjectIdAndStatusAndDueDate(UUID projectId, Status status, LocalDate dueDate, Pageable pageable);
}
