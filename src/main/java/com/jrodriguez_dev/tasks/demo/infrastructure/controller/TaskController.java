package com.jrodriguez_dev.tasks.demo.infrastructure.controller;

import com.jrodriguez_dev.tasks.demo.application.service.TaskService;
import com.jrodriguez_dev.tasks.demo.domain.model.Task;
import com.jrodriguez_dev.tasks.demo.domain.model.TaskStatus;
import com.jrodriguez_dev.tasks.demo.infrastructure.dto.CreateTaskRequest;
import com.jrodriguez_dev.tasks.demo.infrastructure.dto.TaskResponseDTO;
import com.jrodriguez_dev.tasks.demo.infrastructure.persistence.mapper.TaskMapStruct;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapStruct taskMapper;

    public TaskController(TaskService taskService, TaskMapStruct taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> create(@Valid @RequestBody CreateTaskRequest request) {
        Task task = taskMapper.toDomain(request);
        Task created = taskService.createTask(task);
        return ResponseEntity.ok(taskMapper.toDto(created));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> list() {
        List<TaskResponseDTO> tasks = taskService.listTasks().stream()
                .map(taskMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> get(@PathVariable UUID id) {
        return taskService.getTask(id)
                .map(taskMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TaskResponseDTO> updateStatus(@PathVariable UUID id, @RequestParam TaskStatus status) {
        Task updated = taskService.updateStatus(id, status);
        return ResponseEntity.ok(taskMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
