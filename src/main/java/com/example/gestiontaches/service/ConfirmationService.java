package com.example.gestiontaches.service;

import com.example.gestiontaches.entity.ConfirmationToken;
import com.example.gestiontaches.entity.Employee;

public interface ConfirmationService {
    void sendEmployeeVerificationToken(Employee employee);
    ConfirmationToken getEmployeeVerificationToken(String username, String token);
}
