package com.example.ctftreasuremapservice.services;

import com.example.ctftreasuremapservice.model.pojo.User;
import com.example.ctftreasuremapservice.repository.AuthenticationRepo;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {

    private final AuthenticationRepo authenticationRepo;

    public UserDetailsService(AuthenticationRepo authenticationRepo) {
        this.authenticationRepo = authenticationRepo;
    }

    protected boolean userExistCheck(String userName, String password) {
        return authenticationRepo.findByNameAndPassword(userName, password);
    }
}
