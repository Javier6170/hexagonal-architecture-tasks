package com.jrodriguez_dev.tasks.demo.infrastructure.dto;

import com.jrodriguez_dev.tasks.demo.domain.model.TaskStatus;

import java.util.UUID;

public class TaskResponse {
    private UUID id;
    private String title;
    private String description;
    private TaskStatus status;

    public TaskResponse(UUID id, String title, String description, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    // Getters
    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
}
