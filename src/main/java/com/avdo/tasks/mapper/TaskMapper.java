package com.avdo.tasks.mapper;

import com.avdo.tasks.domain.CreateTaskRequest;
import com.avdo.tasks.domain.dto.CreateTaskRequestDto;
import com.avdo.tasks.domain.dto.TaskResponseDto;
import com.avdo.tasks.domain.entities.Task;

public interface TaskMapper {
    CreateTaskRequest fromDto(CreateTaskRequestDto dto);
    TaskResponseDto toDto(Task task);
}
