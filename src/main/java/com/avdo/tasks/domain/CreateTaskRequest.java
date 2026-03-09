package com.avdo.tasks.domain;

import com.avdo.tasks.domain.entities.TaskPriority;

import java.time.LocalDate;

public record CreateTaskRequest(
        String title,
        String description,
        LocalDate dueDate,
        TaskPriority priority

) {
}
