package com.example.gestiontaches.service.impl;

import com.example.gestiontaches.entity.Task;
import com.example.gestiontaches.repository.TaskRepository;
import com.example.gestiontaches.service.TaskService;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void create(Task task) {
        // check Temps Initial > Temps Restant => scheduler
        task.setRt(task.getRt());
        task.setIt(task.getIt());
        this.taskRepository.save(task);
    }

    @Override
    public Task read(Long id) {
        return this.taskRepository.findById(id).orElse(null);
    }

}
