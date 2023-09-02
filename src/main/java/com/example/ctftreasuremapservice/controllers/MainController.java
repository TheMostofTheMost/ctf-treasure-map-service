package com.example.ctftreasuremapservice.controllers;


import com.example.ctftreasuremapservice.manager.ContainerManager;
import com.example.ctftreasuremapservice.manager.LocationManager;
import com.example.ctftreasuremapservice.manager.UserManager;
import com.example.ctftreasuremapservice.model.dto.ContainerDto;
import com.example.ctftreasuremapservice.model.dto.UserDto;
import com.example.ctftreasuremapservice.model.pojo.User;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class MainController {
    private final ContainerManager containerManager;
    private final LocationManager locationManager;
    private final UserManager userManager;

    public MainController(ContainerManager containerManager,
                          LocationManager locationManager,
                          UserManager userManager) {
        this.containerManager = containerManager;
        this.locationManager = locationManager;
        this.userManager = userManager;
    }

    @GetMapping("/")
    public ModelAndView getDefaultPage() {
        ModelAndView modelAndView;
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("admin")) {
            modelAndView = new ModelAndView("main-page-non-authorized.html");
        } else {
            return new ModelAndView(new RedirectView("/main-page"));
        }
        modelAndView.addObject("locations", locationManager.getAll());
        return modelAndView;
    }

    @PostMapping("/container/save")
    public void saveContainerToLocation(@RequestBody ContainerDto containerDto) {
        containerManager.saveContainer(containerDto.getTreasure(), containerDto.getLocationName());
    }

    @GetMapping("/location/data/{locationName}")
    public ModelAndView getLocationData(@PathVariable String locationName) {
        ModelAndView model = new ModelAndView("location-data-fragment.html");
        model.addObject("containers", containerManager.getContainersByLocationName(locationName));
        return model;
    }

    @GetMapping("/auth-page")
    public ModelAndView getAuthForm() {
        return new ModelAndView("authorization-page.html");
    }

    @GetMapping("/main-page")
    public ModelAndView getMainPage() {
        ModelAndView modelAndView = new ModelAndView("main-page-authorized.html");
        modelAndView.addObject("locations", locationManager.getAll());
        modelAndView.addObject("user", SecurityContextHolder.getContext().getAuthentication());
        return modelAndView;
    }


    @GetMapping("/registration")
    public ModelAndView getRegistrationView() {
        return new ModelAndView("registration-page.html");
    }
    @PostMapping("/user/create")
    public ResponseEntity<String> userCreate(@RequestBody UserDto userDto) {
        User user = userManager.fromDto(userDto);
        userManager.save(user);
        return new ResponseEntity<>("Пользователь успешно создан!",
                HttpStatusCode.valueOf(200));
    }
}
