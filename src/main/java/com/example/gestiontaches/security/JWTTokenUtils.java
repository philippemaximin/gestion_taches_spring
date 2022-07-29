package com.example.gestiontaches.security;

import com.example.gestiontaches.entity.Employee;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JWTTokenUtils {

    private String secretKey = "TOT0TOT0TOT0TOT0TOT0TOT0TOT0TOT0TOT0TOT0TOT0TOT0TOT0TOT0";

    public String generateToken(Employee employee) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("firstName", employee.getFirstName());
        claims.put("lastName", employee.getLastName());
        claims.put("roles", employee.getRoles());
        claims.put("userName", employee.getUserName());

        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR, 2);

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(employee.getUsername())
                .setIssuer("GESTIONS TACHES")
                .setIssuedAt(now)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        log.info("Token {}", token);

        return token;
    }

    public String getUserNameFromToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, UserDetails employee) {
        Claims claims = getClaims(token);
        Date expiration = claims.getIssuedAt();
        String userNameFromToken = getUserNameFromToken(token);
        Boolean isValidUserName = userNameFromToken.equals(employee.getUsername());
        Boolean isValidDate = expiration.after(new Date());
        return isValidUserName && isValidDate;
    }
}
