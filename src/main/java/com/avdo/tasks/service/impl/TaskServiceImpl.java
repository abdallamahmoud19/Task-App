package com.avdo.tasks.service.impl;

import com.avdo.tasks.domain.CreateTaskRequest;
import com.avdo.tasks.domain.entities.Task;
import com.avdo.tasks.domain.entities.TaskStatus;
import com.avdo.tasks.repository.TaskRepository;
import com.avdo.tasks.service.TaskService;


import java.time.LocalDate;

public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }
    @Override
    public Task createTask(CreateTaskRequest request) {
        LocalDate now = LocalDate.now();

        Task newTask = new Task(
                null,
                request.title(),
                request.description(),
                request.dueDate(),
                TaskStatus.OPEN,
                request.priority(),
                now,
                now);



        return taskRepository.save(newTask);
    }
}
