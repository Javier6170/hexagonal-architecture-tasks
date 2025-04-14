package com.jrodriguez_dev.tasks.demo.infrastructure.dto;

import com.jrodriguez_dev.tasks.demo.domain.model.TaskStatus;

public class TaskRequestDTO {
    public String title;
    public String description;
    public TaskStatus status;
}
