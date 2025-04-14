package com.jrodriguez_dev.tasks.demo.infrastructure.persistence.repository;

import com.jrodriguez_dev.tasks.demo.infrastructure.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskJpaRepository extends JpaRepository<TaskEntity, UUID> {
}
