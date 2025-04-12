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
}