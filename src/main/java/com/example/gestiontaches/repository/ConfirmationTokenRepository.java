package com.example.gestiontaches.repository;

import com.example.gestiontaches.entity.ConfirmationToken;
import com.example.gestiontaches.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {

    /**
     * SELECT * FROM confirmation_token ct JOIN empployee e ON  e.id = ct.id WHERE ct.value = value AND e.username = :username
     * @param value
     * @param username
     * @return
     */
    Optional<ConfirmationToken> findByValueAndEmployeeUserName(String value, String username);
    Optional<ConfirmationToken> findByValueAndEmployeeUserNameAndActivationNull(String token, String username);
    Optional<ConfirmationToken> findByValueAndEmployeeId(String value, int id);
}
