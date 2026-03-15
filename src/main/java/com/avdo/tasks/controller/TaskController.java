package com.avdo.tasks.controller;

import com.avdo.tasks.domain.CreateTaskRequest;
import com.avdo.tasks.domain.dto.CreateTaskRequestDto;
import com.avdo.tasks.domain.dto.TaskResponseDto;
import com.avdo.tasks.domain.entities.Task;
import com.avdo.tasks.mapper.TaskMapper;
import com.avdo.tasks.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskController {
    private final TaskService taskService;
    private final TaskMapper taskMapper;


    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }
    @PostMapping
    public ResponseEntity<TaskResponseDto>createTask(@Valid @RequestBody CreateTaskRequestDto createTaskRequestDto){
        CreateTaskRequest taskToCreate = taskMapper.fromDto(createTaskRequestDto);
        Task createdTask = taskService.createTask(taskToCreate);
        TaskResponseDto createdTaskDto = taskMapper.toDto(createdTask);
        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);


    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDto>> listOfTasks(){
        List<Task> listTasks = taskService.listOfTask();
        List<TaskResponseDto> taskDtoList= listTasks.stream().map(taskMapper::toDto).toList();
        return ResponseEntity.ok(taskDtoList);


    }
}
