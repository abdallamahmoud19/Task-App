package com.avdo.tasks.domain.dto;

import com.avdo.tasks.domain.entities.TaskPriority;
import com.avdo.tasks.domain.entities.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

public record TaskResponseDto(
        UUID id,
        String title,
        String description,
        LocalDate dueDate,
        TaskPriority priority,
        TaskStatus status) {}
