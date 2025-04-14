package com.jrodriguez_dev.tasks.demo.infrastructure.persistence.mapper;

import com.jrodriguez_dev.tasks.demo.domain.model.Task;
import com.jrodriguez_dev.tasks.demo.infrastructure.dto.CreateTaskRequest;
import com.jrodriguez_dev.tasks.demo.infrastructure.dto.TaskResponseDTO;
import com.jrodriguez_dev.tasks.demo.infrastructure.persistence.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapStruct {
    // De dominio a respuesta DTO
    TaskResponseDTO toDto(Task task);

    // De DTO a dominio
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    @Mapping(target = "status", expression = "java(request.getStatus() != null ? request.getStatus() : com.jrodriguez_dev.tasks.demo.domain.model.TaskStatus.PENDING)")
    Task toDomain(CreateTaskRequest request);

    // De entidad a dominio
    Task toDomain(TaskEntity entity);

    // De dominio a entidad
    TaskEntity toEntity(Task task);
}
