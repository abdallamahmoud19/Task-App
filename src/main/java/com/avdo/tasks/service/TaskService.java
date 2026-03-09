package com.avdo.tasks.service;

import com.avdo.tasks.domain.CreateTaskRequest;
import com.avdo.tasks.domain.entities.Task;

public interface TaskService {
    Task createTask(CreateTaskRequest request);
}
