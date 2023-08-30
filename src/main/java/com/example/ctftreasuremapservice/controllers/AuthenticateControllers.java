package com.example.ctftreasuremapservice.controllers;

import com.example.ctftreasuremapservice.repository.LocationRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AuthenticateControllers {
    private final LocationRepository locationRepository;

    public AuthenticateControllers(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    @GetMapping("/auth-page")
    public ModelAndView getAuthForm() {
        return new ModelAndView("authorization-page.html");
    }

    @GetMapping("/main-page")
    public ModelAndView getMainPage() {
        ModelAndView modelAndView = new ModelAndView("main-page-authorized.html");
        modelAndView.addObject("containers", List.of("2", "5", "7",
                "19", "11"));
        modelAndView.addObject("locations", locationRepository.getAll());
        modelAndView.addObject("user", SecurityContextHolder.getContext().getAuthentication());
        return modelAndView;
    }

}
