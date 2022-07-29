package com.example.gestiontaches.service.impl;

import com.example.gestiontaches.entity.ConfirmationToken;
import com.example.gestiontaches.entity.Employee;
import com.example.gestiontaches.exceptions.BadRequestException;
import com.example.gestiontaches.repository.ConfirmationTokenRepository;
import com.example.gestiontaches.service.ConfirmationService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class ConfirmationServiceImpl implements ConfirmationService {
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void sendEmployeeVerificationToken(Employee employee) {
        //Generer un token
        String validationToken = RandomStringUtils.random(6, false, true);

        //Sauvegarder le token
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setValue(validationToken);

        confirmationToken.setEmployee(employee);
        confirmationToken.setCreation(Instant.now());
        confirmationTokenRepository.save(confirmationToken);

        //Envoyer le token SMS/EMAIL
    }

    @Override
    public ConfirmationToken getEmployeeVerificationToken(String username, String token) {
        ConfirmationToken confirmationToken = this.confirmationTokenRepository
                .findByValueAndEmployeeUserNameAndActivationNull(token, username)
                .orElseThrow(() -> new BadRequestException(null, "Le compte est ou votre code est invalide"));

        if (ChronoUnit.MINUTES.between(confirmationToken.getCreation(), Instant.now()) >10) {
            throw new BadRequestException(null, "Le delai d'activation est depasse");
        }

        return confirmationToken;
    }


}
