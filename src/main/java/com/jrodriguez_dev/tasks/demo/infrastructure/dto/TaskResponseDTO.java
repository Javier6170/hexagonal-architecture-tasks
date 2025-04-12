package com.jrodriguez_dev.tasks.demo.infrastructure.dto;

import com.jrodriguez_dev.tasks.demo.domain.model.TaskStatus;

import java.util.UUID;

public class TaskResponseDTO {
    public UUID id;
    public String title;
    public String description;
    public TaskStatus status;
}
