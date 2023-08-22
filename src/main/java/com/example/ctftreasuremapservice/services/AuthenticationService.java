package com.example.ctftreasuremapservice.services;

import com.example.ctftreasuremapservice.model.pojo.User;
import com.example.ctftreasuremapservice.repository.TestRepo;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final TestRepo authRepositiry;

    public AuthenticationService(TestRepo authRepositiry) {
        this.authRepositiry = authRepositiry;
    }

    public boolean userExistCheck(User user) {
        String userExistCheckSqlRequest = "SELECT * FROM user_tabel WHERE name ='" + user.getUsername()
                + "' AND password ='" + user.getPassword() + "'";
        return authRepositiry.executeQuery(userExistCheckSqlRequest);
    }
}
