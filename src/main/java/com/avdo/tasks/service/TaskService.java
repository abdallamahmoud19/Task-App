package com.avdo.tasks.service;

import com.avdo.tasks.domain.CreateTaskRequest;
import com.avdo.tasks.domain.UpdateTaskRequest;
import com.avdo.tasks.domain.entities.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TaskService {
    Task createTask(CreateTaskRequest request);
    List<Task> listOfTask();
    Task updateTask(UUID taskId, UpdateTaskRequest request);
    void deleteTask(UUID taskId);

}
