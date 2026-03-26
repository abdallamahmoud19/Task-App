package com.avdo.tasks.mapper.impl;

import com.avdo.tasks.domain.CreateTaskRequest;
import com.avdo.tasks.domain.UpdateTaskRequest;
import com.avdo.tasks.domain.dto.CreateTaskRequestDto;
import com.avdo.tasks.domain.dto.TaskResponseDto;
import com.avdo.tasks.domain.dto.UpdateTaskRequestDto;
import com.avdo.tasks.domain.entities.Task;
import com.avdo.tasks.mapper.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public CreateTaskRequest fromDto(CreateTaskRequestDto dto) {
        return new CreateTaskRequest(
                dto.title(),
                dto.description(),
                dto.dueDate(),
                dto.priority()
        );
    }

    @Override
    public TaskResponseDto toDto(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getTaskPriority(),
                task.getStatus());
    }

    @Override
    public UpdateTaskRequest fromDto(UpdateTaskRequestDto dto) {
        return new UpdateTaskRequest(dto.title(),
                dto.description(),
                dto.dueDate(),
                dto.priority(),
                dto.status());

    }
}
