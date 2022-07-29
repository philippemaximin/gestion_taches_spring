package com.example.gestiontaches.service.impl;

import com.example.gestiontaches.entity.Employee;
import com.example.gestiontaches.entity.Task;
import com.example.gestiontaches.exceptions.BadRequestException;
import com.example.gestiontaches.repository.EmployeeRepository;
import com.example.gestiontaches.service.EmployeeService;
import com.example.gestiontaches.service.TaskService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.gestiontaches.enums.ErrorCode.TASK_AFFETED;
import static com.example.gestiontaches.enums.ErrorCode.USER_EXIST;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private TaskService taskService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, TaskService taskService) {
        this.employeeRepository = employeeRepository;
        this.taskService = taskService;
    }

    @Override
    public List<Employee> search(String q) {
        if (q == null) {
            return this.employeeRepository.findAll();
        }

        return this.employeeRepository.search(q).collect(Collectors.toList());
    }

    @Override
    public void create(Employee employee) {

        Optional<Employee> employeeOptional = this.employeeRepository.findByUserName(employee.getUserName());

        if (employeeOptional.isPresent()) {
            throw new BadRequestException(USER_EXIST, "L'utilisateur existe deja avec cette adresse email");
        }

        this.employeeRepository.save(employee);
    }

    @Override
    public Employee read(Long id) {
        return this.employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee getByUserName(String username) {
        return this.employeeRepository.findByUserName(username).orElseThrow(null);
    }

    @Override
    public Employee update(Employee employee, Long id) {
        Employee currentEmployee = this.read(id);

        currentEmployee.setFirstName(employee.getFirstName());
        currentEmployee.setLastName(employee.getLastName());
        currentEmployee.setUserName(employee.getUserName());

        return currentEmployee;
    }

    @Override
    public void delete(Long id) {
        this.employeeRepository.deleteById(id);
    }

    @Override
    public void taskToUser(Long taskId, Long employeeId) {
        //tache
        Task task = this.taskService.read(taskId);

        //user
        Employee employee = this.read(employeeId);

        Optional<Task> existingTask = employee.getTask()
                .stream()
                .filter(userTask -> task.getId() == taskId)
                .findFirst();


        if (existingTask.isPresent()) {
            throw new BadRequestException(TASK_AFFETED, "Cette est deja attribué a l'utilisateur");
        }

        employee.getTask().add(task);
    }
}
