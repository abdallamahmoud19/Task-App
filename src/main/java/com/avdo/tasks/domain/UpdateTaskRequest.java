package com.avdo.tasks.domain;

import com.avdo.tasks.domain.entities.TaskPriority;
import com.avdo.tasks.domain.entities.TaskStatus;

import java.time.LocalDate;

public record UpdateTaskRequest(
        String title,
        String description,
        LocalDate dueDate,
        TaskPriority priority,
        //To Change task from OPEN to COMPLETED
        TaskStatus status
) {


}
