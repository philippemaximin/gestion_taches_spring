package com.example.gestiontaches.service.impl;

import com.example.gestiontaches.dto.AuthentificationDTO;
import com.example.gestiontaches.dto.TokenDto;
import com.example.gestiontaches.entity.ConfirmationToken;
import com.example.gestiontaches.entity.Employee;
import com.example.gestiontaches.entity.Role;
import com.example.gestiontaches.enums.ErrorCode;
import com.example.gestiontaches.enums.UserRole;
import com.example.gestiontaches.exceptions.BadRequestException;
import com.example.gestiontaches.repository.RoleRepository;
import com.example.gestiontaches.security.JWTTokenUtils;
import com.example.gestiontaches.service.AccountService;
import com.example.gestiontaches.service.ConfirmationService;
import com.example.gestiontaches.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private RoleRepository roleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private EmployeeService employeeService;
    private ConfirmationService confirmationService;
    private JWTTokenUtils jwtTokenUtils;


    public AccountServiceImpl(RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EmployeeService employeeService, ConfirmationService confirmationService, JWTTokenUtils jwtTokenUtils) {
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.employeeService = employeeService;
        this.confirmationService = confirmationService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    public void signup(Employee employee) {

        //Check password
        if (employee.getPassword() == null && employee.getPassword().length() < 8) {
            throw new BadRequestException(ErrorCode.PASSWORD_REFUSE, "Mot de passe invalide");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(employee.getPassword());

        log.info("Mot de passe en clair {} mot de passe crypté {}", employee.getPassword(), encodedPassword);
        employee.setPassword(encodedPassword);

        Role role = this.roleRepository.findByLabel(UserRole.USER).orElse(null);

        List<Role> currentRole = employee.getRoles();

        currentRole.add(role);

        employee.setRoles(currentRole);

        employee.setEnabled(false);
        employeeService.create(employee);

        confirmationService.sendEmployeeVerificationToken(employee);
    }

    @Transactional
    @Override
    public void activate(String username, String token) {
        ConfirmationToken confirmationToken = this.confirmationService.getEmployeeVerificationToken(username, token);
        Employee employee = this.employeeService.getByUserName(username);

        if (confirmationToken.getEmployee().getId() != employee.getId()) {
            throw new BadRequestException(ErrorCode.INVALID_TOKEN, "Token invalide");
        }

        confirmationToken.setActivation(Instant.now());
        employee.setEnabled(true);
    }


    @Override
    public TokenDto generateTokens(AuthentificationDTO authentificationDTO) {
        Employee employee = this.employeeService.getByUserName(authentificationDTO.getUsername());
        String authenticationToken = this.jwtTokenUtils.generateToken(employee);
        String refreshToken = RandomStringUtils.random(40, true, true);

        TokenDto tokens = new TokenDto();

        // Stocker un objet AUTHDATA ()


        tokens.setAuthentification(authenticationToken);
        tokens.setRefresh(refreshToken);

        return tokens;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.employeeService.getByUserName(username);
    }
}
