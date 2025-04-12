package com.jrodriguez_dev.tasks.demo.domain.model;

import java.util.UUID;

public class Task {
    private final UUID id;
    private final String title;
    private final String description;
    private TaskStatus status;

    public Task(UUID id, String title, String description, TaskStatus status) {
        this.id = id; // puede ser null
        this.title = title;
        this.description = description;
        this.status = status != null ? status : TaskStatus.PENDING;
    }

    // Comportamiento del negocio
    public void markAsInProgress() {
        if (status == TaskStatus.PENDING) {
            this.status = TaskStatus.IN_PROGRESS;
        }
    }

    public void markAsCompleted() {
        if (status == TaskStatus.IN_PROGRESS) {
            this.status = TaskStatus.COMPLETED;
        }
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }
}
