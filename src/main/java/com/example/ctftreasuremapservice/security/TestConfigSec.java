package com.example.ctftreasuremapservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;


@Configuration
@EnableWebSecurity
public class TestConfigSec {

    private final CheckUserAuthNEW checkUserAuthNEW;

    public TestConfigSec(CheckUserAuthNEW checkUserAuthNEW) {
        this.checkUserAuthNEW = checkUserAuthNEW;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> {
                    request.requestMatchers("/main-page").authenticated();
                    request.anyRequest().permitAll();
                })
                .formLogin((httpSecurityFormLoginConfigurer ->
                {httpSecurityFormLoginConfigurer.loginProcessingUrl("/login").permitAll();
                httpSecurityFormLoginConfigurer.defaultSuccessUrl("/main-page");}))
                .csrf((AbstractHttpConfigurer::disable))
                .authenticationProvider(checkUserAuthNEW)
                .sessionManagement((httpSecuritySessionManagementConfigurer -> {
                    httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
                    httpSecuritySessionManagementConfigurer.sessionFixation().migrateSession();
                }));

        return http.build();
    }


    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }


}
