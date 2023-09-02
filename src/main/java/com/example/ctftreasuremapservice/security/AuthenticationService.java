package com.example.ctftreasuremapservice.security;

import com.example.ctftreasuremapservice.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Configuration
public class AuthenticationService implements AuthenticationProvider {
    private final JdbcTemplate jdbcTemplate;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.info("[" + LocalDate.now() + "]" + "[INFO] from " + Utils.getRequestRemoteAddr() + " /login > [AUTH] [LOGIN] ");
        String userExistCheckSqlRequest = "SELECT * FROM user_table WHERE username ='" + authentication.getName()
                + "' AND password ='" + authentication.getCredentials().toString() + "'";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(userExistCheckSqlRequest);

        if (!rows.isEmpty() && (boolean) rows.get(0).get("is_admin")) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(),
                    authentication.getCredentials().toString(),
                    Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
        } else if (!rows.isEmpty()) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(),
                    authentication.getCredentials().toString(),
                    Collections.singletonList(new SimpleGrantedAuthority("USER")));
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
