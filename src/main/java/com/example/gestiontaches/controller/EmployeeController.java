package com.example.gestiontaches.controller;

import com.example.gestiontaches.dto.UserTaskDto;
import com.example.gestiontaches.entity.Employee;
import com.example.gestiontaches.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "employee",  produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@RequestBody Employee employee) {
        this.employeeService.create(employee);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/{employeeId}/task")
    public void taskToUser(@Validated @RequestBody UserTaskDto userTaskDto, @PathVariable Long employeeId) {
        this.employeeService.taskToUser(
                userTaskDto.getTaskId(),
                employeeId
        );
    }

    @GetMapping
    public List<Employee> getAll() {
        return this.employeeService.search(null);
    }
}
