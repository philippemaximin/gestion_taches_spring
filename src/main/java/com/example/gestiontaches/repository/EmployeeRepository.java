package com.example.gestiontaches.repository;

import com.example.gestiontaches.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.stream.Stream;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUserName(String username);

    @Query("SELECT e FROM Employee e where e.userName like CONCAT ('%', :username, '%')")
    Stream<Employee> search(@Param("username") String username);
}
