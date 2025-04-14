package com.jrodriguez_dev.tasks.demo.infrastructure.dto;

import com.jrodriguez_dev.tasks.demo.domain.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequest {
    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String description;

    private TaskStatus status;
}
