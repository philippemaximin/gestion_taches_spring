package com.example.gestiontaches.service;

import com.example.gestiontaches.entity.Task;

public interface TaskService {
    void create(Task task);
    Task read(Long id);
}
