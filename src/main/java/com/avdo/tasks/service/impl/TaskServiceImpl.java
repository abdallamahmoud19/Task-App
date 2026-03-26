package com.avdo.tasks.service.impl;

import com.avdo.tasks.domain.CreateTaskRequest;
import com.avdo.tasks.domain.UpdateTaskRequest;
import com.avdo.tasks.domain.entities.Task;
import com.avdo.tasks.domain.entities.TaskStatus;
import com.avdo.tasks.exception.TaskNotFoundException;
import com.avdo.tasks.repository.TaskRepository;
import com.avdo.tasks.service.TaskService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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

    @Override
    public Task updateTask(UUID taskId, UpdateTaskRequest request) {
       Task task=  taskRepository.findById(taskId).orElseThrow(()-> new TaskNotFoundException(taskId));
       task.setTitle(request.title());
       task.setDescription(request.description());
       task.setStatus(request.status());
       task.setTaskPriority(request.priority());
       task.setUpdated(LocalDate.now());
       return  taskRepository.save(task);
    }


}
