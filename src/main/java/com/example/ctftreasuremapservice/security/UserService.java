package com.example.ctftreasuremapservice.security;

import com.example.ctftreasuremapservice.model.pojo.User;
import com.example.ctftreasuremapservice.services.AuthenticationService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService{

    private final AuthenticationService authenticationService;

    public UserService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public UserDetails loadUserByUsername(User user) throws UsernameNotFoundException {
        if (!authenticationService.userExistCheck(user)) {
            throw new UsernameNotFoundException("Пользователь не найден: " + user.getUsername());
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
