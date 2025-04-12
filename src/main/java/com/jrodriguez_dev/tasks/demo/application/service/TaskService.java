package com.jrodriguez_dev.tasks.demo.application.service;

import com.jrodriguez_dev.tasks.demo.domain.model.Task;
import com.jrodriguez_dev.tasks.demo.domain.model.TaskStatus;
import com.jrodriguez_dev.tasks.demo.domain.port.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTask(UUID id) {
        return taskRepository.findById(id);
    }

    public List<Task> listTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id);
    }

    public Task updateStatus(UUID id, TaskStatus newStatus) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));

        switch (newStatus) {
            case IN_PROGRESS -> task.markAsInProgress();
            case COMPLETED -> task.markAsCompleted();
        }

        return taskRepository.save(task);
    }
}
