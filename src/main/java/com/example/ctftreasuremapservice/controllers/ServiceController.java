//package com.example.ctftreasuremapservice.controllers;
//
//import com.example.ctftreasuremapservice.manager.ContainerManager;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//
//@Controller("/container")
//public class ServiceController {
//    private final ContainerManager containerManager;
//    public ServiceController(ContainerManager containerManager) {
//        this.containerManager = containerManager;
//    }
//
//    @PostMapping("/save")
//    public ModelAndView saveContainerToLocation(@RequestParam String treasure, @RequestParam String locationName) {
//        containerManager.saveContainer(treasure, locationName);
//        return null;
//    }
//}
