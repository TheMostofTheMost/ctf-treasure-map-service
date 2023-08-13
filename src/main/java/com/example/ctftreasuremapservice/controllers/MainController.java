package com.example.ctftreasuremapservice.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MainController {

    @GetMapping("/")
    public ModelAndView getMainPage() {
        ModelAndView modelAndView = new ModelAndView("main-page-non-authorized.html");
//        ModelAndView modelAndView = new ModelAndView("main-page-authorized.html");
        List<Integer> test = List.of(7, 4, 3, 8, 5);
        modelAndView.addObject("containers", test);
        return modelAndView;
    }


}
