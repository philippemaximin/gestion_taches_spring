package com.example.gestiontaches.controller;

import com.example.gestiontaches.entity.Task;
import com.example.gestiontaches.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "task",  produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody Task task) {
        this.taskService.create(task);
    }

    @GetMapping(path = "/{id}")
    public Task read(@PathVariable(value = "id") Long id) {
        return this.taskService.read(id);
    }
}
