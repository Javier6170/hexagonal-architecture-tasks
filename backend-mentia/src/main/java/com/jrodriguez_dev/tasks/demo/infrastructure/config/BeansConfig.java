package com.jrodriguez_dev.tasks.demo.infrastructure.config;

import com.jrodriguez_dev.tasks.demo.application.service.TaskService;
import com.jrodriguez_dev.tasks.demo.domain.port.TaskRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public TaskService taskService(TaskRepository taskRepository) {
        return new TaskService(taskRepository);
    }
}
