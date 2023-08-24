package com.example.ctftreasuremapservice.controllers;


import com.example.ctftreasuremapservice.manager.ContainerManager;
import com.example.ctftreasuremapservice.model.dto.ContainerDto;
import com.example.ctftreasuremapservice.model.entity.ContainerEntity;
import com.example.ctftreasuremapservice.repository.LocationRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MainController {
    private final ContainerManager containerManager;
    private final LocationRepository locationRepository;

    public MainController(ContainerManager containerManager, LocationRepository locationRepository) {
        this.containerManager = containerManager;
        this.locationRepository = locationRepository;
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
        modelAndView.addObject("locations", locationRepository.getAll());
        return modelAndView;
    }

    @PostMapping("/container/save")
    public void saveContainerToLocation(@RequestBody ContainerDto containerDto) {
        containerManager.saveContainer(containerDto.getTreasure(), containerDto.getLocationName());
    }

    @GetMapping("/location/data/{locationName}")
    public ModelAndView getLocationData(@PathVariable String locationName) {
        ModelAndView model = new ModelAndView("location-data-fragment.html");
        List<ContainerEntity> qwe = containerManager.getContainerByLocationName(locationName);
        model.addObject("containers", containerManager.getContainerByLocationName(locationName));
        return model;
    }

}
