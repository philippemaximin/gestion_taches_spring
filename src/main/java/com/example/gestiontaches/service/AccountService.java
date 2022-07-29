package com.example.gestiontaches.service;

import com.example.gestiontaches.dto.AuthentificationDTO;
import com.example.gestiontaches.dto.TokenDto;
import com.example.gestiontaches.entity.Employee;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {
    void signup(Employee employee);
    void activate(String username, String token);

    TokenDto generateTokens(AuthentificationDTO authentificationDTO);
}
