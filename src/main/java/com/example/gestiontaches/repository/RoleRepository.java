package com.example.gestiontaches.repository;

import com.example.gestiontaches.entity.Role;
import com.example.gestiontaches.entity.Task;
import com.example.gestiontaches.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByLabel(UserRole label);
}
