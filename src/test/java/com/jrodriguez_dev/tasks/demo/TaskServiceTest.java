package com.jrodriguez_dev.tasks.demo;

import com.jrodriguez_dev.tasks.demo.application.service.TaskService;
import com.jrodriguez_dev.tasks.demo.domain.model.Task;
import com.jrodriguez_dev.tasks.demo.domain.model.TaskStatus;
import com.jrodriguez_dev.tasks.demo.domain.port.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    @Test
    void shouldCreateTask() {
        Task taskToSave = new Task(UUID.randomUUID(), "Test", "desc", TaskStatus.PENDING);

        when(repository.save(any())).thenReturn(taskToSave);

        Task result = service.createTask(taskToSave);

        assertEquals("Test", result.getTitle());
        verify(repository).save(any());
    }

    @Test
    void shouldReturnAllTasks() {
        List<Task> tasks = List.of(
                new Task(UUID.randomUUID(), "Task 1", "Description", TaskStatus.PENDING),
                new Task(UUID.randomUUID(), "Task 2", "Description", TaskStatus.COMPLETED)
        );

        when(repository.findAll()).thenReturn(tasks);

        List<Task> result = service.listTasks();

        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getTitle());
        verify(repository).findAll();
    }

    @Test
    void shouldReturnTaskById() {
        UUID id = UUID.randomUUID();
        Task task = new Task(id, "Test", "desc", TaskStatus.PENDING);

        when(repository.findById(id)).thenReturn(Optional.of(task));

        Optional<Task> result = service.getTask(id);

        assertEquals("Test", result.get().getTitle());
        verify(repository).findById(id);
    }

    @Test
    void shouldDeleteTaskById() {
        UUID id = UUID.randomUUID();

        service.deleteTask(id);

        verify(repository).deleteById(id);
    }

    @Test
    void shouldUpdateTaskStatusToInProgress() {
        UUID id = UUID.randomUUID();
        Task task = new Task(id, "Test Task", "Desc", TaskStatus.PENDING);

        when(repository.findById(id)).thenReturn(Optional.of(task));
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Task updatedTask = service.updateStatus(id, TaskStatus.IN_PROGRESS);

        assertEquals(TaskStatus.IN_PROGRESS, updatedTask.getStatus());
        verify(repository).findById(id);
        verify(repository).save(any());
    }

    @Test
    void shouldUpdateTaskStatusToCompleted() {
        UUID id = UUID.randomUUID();
        Task task = new Task(id, "Another Task", "Desc", TaskStatus.IN_PROGRESS);

        when(repository.findById(id)).thenReturn(Optional.of(task));
        when(repository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Task updatedTask = service.updateStatus(id, TaskStatus.COMPLETED);

        assertEquals(TaskStatus.COMPLETED, updatedTask.getStatus());
        verify(repository).findById(id);
        verify(repository).save(any());
    }

    @Test
    void shouldThrowExceptionWhenTaskNotFound() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = org.junit.jupiter.api.Assertions.assertThrows(
                RuntimeException.class,
                () -> service.updateStatus(id, TaskStatus.IN_PROGRESS)
        );

        assertEquals("Tarea no encontrada", exception.getMessage());
        verify(repository).findById(id);
    }






}