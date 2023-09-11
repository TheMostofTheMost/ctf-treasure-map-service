package com.example.ctftreasuremapservice.controllers;


import com.example.ctftreasuremapservice.manager.ContainerManager;
import com.example.ctftreasuremapservice.manager.LocationManager;
import com.example.ctftreasuremapservice.manager.UserManager;
import com.example.ctftreasuremapservice.model.dto.ContainerDto;
import com.example.ctftreasuremapservice.model.dto.UserDto;
import com.example.ctftreasuremapservice.model.pojo.User;
import com.example.ctftreasuremapservice.security.AuthenticationService;
import com.example.ctftreasuremapservice.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;

@Controller
public class MainController {
    private final ContainerManager containerManager;
    private final LocationManager locationManager;
    private final UserManager userManager;
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);


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
        if (!userManager.isAdmin()) {
            modelAndView = new ModelAndView("main-page-non-authorized.html");
        } else {
            return new ModelAndView(new RedirectView("/main-page"));
        }
        modelAndView.addObject("locations", locationManager.getAll());
        return modelAndView;
    }

    @PostMapping("/container/save")
    public void saveContainerToLocation(@RequestBody ContainerDto containerDto) {
        try {
            containerManager.saveContainer(containerDto.getTreasure(), containerDto.getLocationName());
        } catch (Exception e) {
            logger.error("[" + LocalDate.now() + "]" + "[ERROR] from " + Utils.getRequestRemoteAddr() + " with username = " + SecurityContextHolder.getContext().getAuthentication().getPrincipal() + " /container/save > [MainController] [ERROR]");
        }
        logger.info("[" + LocalDate.now() + "]" + "[INFO] from " + Utils.getRequestRemoteAddr() + " with username = " + SecurityContextHolder.getContext().getAuthentication().getPrincipal() + " /container/save > [MainController] [SUCCESS]");
    }

    @GetMapping("/location/data/{locationName}")
    public ModelAndView getLocationData(@PathVariable String locationName) {
        ModelAndView model = new ModelAndView("location-data-fragment.html");
        try {
            model.addObject("containers", containerManager.getContainersByLocationName(locationName));
        } catch (Exception e) {
            logger.error("[" + LocalDate.now() + "]" + "[ERROR] from " + Utils.getRequestRemoteAddr() + " with username = " + SecurityContextHolder.getContext().getAuthentication().getPrincipal() + " /location/data/{locationName} > [MainController] [ERROR]");
        }
        logger.info("[" + LocalDate.now() + "]" + "[INFO] from " + Utils.getRequestRemoteAddr() + " with username = " + SecurityContextHolder.getContext().getAuthentication().getPrincipal() + " /location/data/{locationName} > [MainController] [SUCCESS]");

        return model;
    }

    @GetMapping("/auth-page")
    public ModelAndView getAuthForm() {
        return new ModelAndView("authorization-page.html");
    }

    @GetMapping("/main-page")
    public ModelAndView getMainPage() {
        ModelAndView modelAndView = new ModelAndView("main-page-authorized.html");
        try {
            modelAndView.addObject("locations", locationManager.getAll());
            modelAndView.addObject("user", SecurityContextHolder.getContext().getAuthentication());
        } catch (Exception e) {
            logger.error("[" + LocalDate.now() + "]" + "[ERROR] from " + Utils.getRequestRemoteAddr() + " with username = " + SecurityContextHolder.getContext().getAuthentication().getPrincipal() + " /main-page > [MainController] [ERROR]");
        }
        logger.info("[" + LocalDate.now() + "]" + "[INFO] from " + Utils.getRequestRemoteAddr() + " with username = " + SecurityContextHolder.getContext().getAuthentication().getPrincipal() + " /main-page > [MainController] [SUCCESS]");
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView getRegistrationView() {
        return new ModelAndView("registration-page.html");
    }

    @PostMapping("/user/create")
    public ResponseEntity<String> userCreate(@RequestBody UserDto userDto) {
        try {
            User user = userManager.fromDto(userDto);
            userManager.save(user);
        } catch (Exception e) {
            logger.error("[" + LocalDate.now() + "]" + "[ERROR] from " + Utils.getRequestRemoteAddr() + " with username = " + SecurityContextHolder.getContext().getAuthentication().getPrincipal() + " /user/create > [MainController] [ERROR]");
        }
        logger.info("[" + LocalDate.now() + "]" + "[INFO] from " + Utils.getRequestRemoteAddr() + " with username = " + SecurityContextHolder.getContext().getAuthentication().getPrincipal() + " /user/create > [MainController] [SUCCESS]");
        return new ResponseEntity<>("Пользователь успешно создан!",
                HttpStatusCode.valueOf(200));
    }
}
