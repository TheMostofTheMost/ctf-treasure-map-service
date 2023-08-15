package com.example.ctftreasuremapservice.controllers;

import com.example.ctftreasuremapservice.model.dto.UserDto;
import com.example.ctftreasuremapservice.services.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticateControllers {
    private final AuthenticationService authenticationService;

    public AuthenticateControllers(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/authentication")
    public ModelAndView getAuthForm() {
        return new ModelAndView("authenticate-fragment.html");
    }

    @GetMapping("/registration")
    public ModelAndView getRegForm() {
        return new ModelAndView("registration-fragment.html");
    }

    @PostMapping("/login")
    public ModelAndView auth(@RequestBody UserDto user) {
        ModelAndView modelAndView ;
        if (authenticationService.userCheck(user)) {

            String jwt = authenticationService.generateToken(user.getUserName());
            modelAndView = new ModelAndView("main-page-authorized.html");
            //добавить объект для отрисовки
        } else {
            // отправлять на окно авторизации опять
            modelAndView = new ModelAndView("main-page-non-authorized.html");
        }
        return
    }
}
