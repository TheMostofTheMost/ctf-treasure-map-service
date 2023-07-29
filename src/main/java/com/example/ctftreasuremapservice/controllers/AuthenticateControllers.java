package com.example.ctftreasuremapservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticateControllers {

    @GetMapping("/authentication")
    public ModelAndView getAuthForm() {
        return new ModelAndView("authenticate-fragment.html");
    }
    @GetMapping("/registration")
    public ModelAndView getRegForm() {
        return new ModelAndView("registration-fragment.html");
    }
}
