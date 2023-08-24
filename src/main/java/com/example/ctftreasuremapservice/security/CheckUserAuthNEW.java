package com.example.ctftreasuremapservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Map;


@Configuration
public class CheckUserAuthNEW implements AuthenticationProvider {
    private final JdbcTemplate jdbcTemplate;

    public CheckUserAuthNEW(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userExistCheckSqlRequest = "SELECT * FROM user_tabel WHERE name ='" + authentication.getName()
                + "' AND password ='" + authentication.getCredentials().toString() + "'";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(userExistCheckSqlRequest);

        if (!rows.isEmpty()) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(),
                    authentication.getCredentials().toString(),
                    Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
