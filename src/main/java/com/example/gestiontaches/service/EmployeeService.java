package com.example.gestiontaches.service;

import com.example.gestiontaches.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> search(String q);
    void create(Employee employee);
    Employee read(Long id);
    Employee getByUserName(String username);
    Employee update(Employee employee, Long id);
    void delete(Long id);

    void taskToUser(Long taskId, Long employeeId);
}
