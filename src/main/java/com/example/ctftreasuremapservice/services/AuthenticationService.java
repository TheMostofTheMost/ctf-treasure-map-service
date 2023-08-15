package com.example.ctftreasuremapservice.services;

import com.example.ctftreasuremapservice.model.dto.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import static javax.crypto.Cipher.SECRET_KEY;

@Service
public class AuthenticationService {
    private final UserDetailsService userDetailsService;

    public AuthenticationService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    public boolean userCheck(UserDto user) {
        return userDetailsService.userExistCheck(user.getUserName(), user.getPassword());
    }

    public String generateToken(String username) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + 86400000); // Токен действителен в течение 24 часов

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, generateSecretKey())
                .compact();
    }

    public String generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);
        return  Base64.getEncoder().encodeToString(keyBytes);
    }
}
