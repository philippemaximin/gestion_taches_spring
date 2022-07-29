package com.example.gestiontaches.repository;

import com.example.gestiontaches.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
