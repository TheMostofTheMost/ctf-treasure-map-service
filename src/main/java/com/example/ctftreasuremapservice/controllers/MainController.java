package com.example.ctftreasuremapservice.controllers;


import com.example.ctftreasuremapservice.manager.ContainerManager;
import com.example.ctftreasuremapservice.model.dto.ContainerDto;
import com.example.ctftreasuremapservice.model.entity.ContainerEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MainController {
    private final ContainerManager containerManager;

    public MainController(ContainerManager containerManager) {
        this.containerManager = containerManager;
    }


    @GetMapping("/")
    public ModelAndView getMainPage() {
        ModelAndView modelAndView;
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("admin")) {
            modelAndView = new ModelAndView("main-page-non-authorized.html");
        } else {
            return new ModelAndView(new RedirectView("/main-page"));
        }
        List<Integer> test = List.of(7, 4, 3, 8, 5);
        modelAndView.addObject("containers", test);
        return modelAndView;
    }

    @PostMapping("/container/save")
    public ModelAndView saveContainerToLocation(@RequestBody ContainerDto containerDto) {
        containerManager.saveContainer(containerDto.getTreasure(), containerDto.getLocationName());
        return null;
    }

    @GetMapping("/location/data/{locationName}")
    public ModelAndView getLocationData(@PathVariable String locationName) {
        ModelAndView model = new ModelAndView("location-data-fragment.html");
        List<ContainerEntity> qwe = containerManager.getContainerByLocationName(locationName);
        model.addObject("containers", containerManager.getContainerByLocationName(locationName));
        return model;
    }

}
