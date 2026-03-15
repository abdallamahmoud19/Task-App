package com.avdo.tasks.service.impl;

import com.avdo.tasks.domain.CreateTaskRequest;
import com.avdo.tasks.domain.entities.Task;
import com.avdo.tasks.domain.entities.TaskStatus;
import com.avdo.tasks.repository.TaskRepository;
import com.avdo.tasks.service.TaskService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
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

    @Override
    public List<Task> listOfTask() {
        return taskRepository.findAll(Sort.by(Sort.Direction.ASC,"created"));
    }
}
