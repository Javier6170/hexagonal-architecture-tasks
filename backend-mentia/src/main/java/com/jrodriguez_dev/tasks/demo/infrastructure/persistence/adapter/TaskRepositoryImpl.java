package com.jrodriguez_dev.tasks.demo.infrastructure.persistence.adapter;

import com.jrodriguez_dev.tasks.demo.domain.model.Task;
import com.jrodriguez_dev.tasks.demo.domain.port.TaskRepository;
import com.jrodriguez_dev.tasks.demo.infrastructure.persistence.entity.TaskEntity;
import com.jrodriguez_dev.tasks.demo.infrastructure.persistence.mapper.TaskMapStruct;
import com.jrodriguez_dev.tasks.demo.infrastructure.persistence.repository.TaskJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;



@Repository
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskJpaRepository taskJpaRepository;
    private final TaskMapStruct taskMapper;

    public TaskRepositoryImpl(TaskJpaRepository taskJpaRepository, TaskMapStruct taskMapper) {
        this.taskJpaRepository = taskJpaRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public Task save(Task task) {
        TaskEntity entity = taskMapper.toEntity(task);
        TaskEntity saved = taskJpaRepository.save(entity);
        return taskMapper.toDomain(saved);
    }

    @Override
    public Optional<Task> findById(UUID id) {
        return taskJpaRepository.findById(id)
                .map(taskMapper::toDomain);
    }

    @Override
    public List<Task> findAll() {
        return taskJpaRepository.findAll().stream()
                .map(taskMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        taskJpaRepository.deleteById(id);
    }

}
