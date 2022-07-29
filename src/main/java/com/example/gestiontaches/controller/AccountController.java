package com.example.gestiontaches.controller;

import com.example.gestiontaches.dto.ActivationDTO;
import com.example.gestiontaches.dto.AuthentificationDTO;
import com.example.gestiontaches.dto.TokenDto;
import com.example.gestiontaches.entity.Employee;
import com.example.gestiontaches.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;

@RestController
public class AccountController {

    private AuthenticationManager authenticationManager;
    private AccountService accountService;

    public AccountController(AuthenticationManager authenticationManager, AccountService accountService) {
        this.authenticationManager = authenticationManager;
        this.accountService = accountService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = "signin", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody TokenDto signin(@RequestBody AuthentificationDTO authentificationDTO) {
        // auth via spring security
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authentificationDTO.getUsername()
                        , authentificationDTO.getPassword()
                )
        );

        //Generer token
        return this.accountService.generateTokens(authentificationDTO);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "signup", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void signup(@RequestBody Employee employee) {
        this.accountService.signup(employee);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(path = "activate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void activate(@RequestBody ActivationDTO activationDTO) {
        this.accountService.activate(activationDTO.getUsername(), activationDTO.getToken());
    }
}
