package com.example.ctftreasuremapservice.controllers;

import com.example.ctftreasuremapservice.model.dto.UserDto;
import com.example.ctftreasuremapservice.model.entity.LocationEntity;
import com.example.ctftreasuremapservice.model.pojo.User;
import com.example.ctftreasuremapservice.repository.JwtRepo;
import com.example.ctftreasuremapservice.repository.LocationRepository;
import com.example.ctftreasuremapservice.repository.TestRepo;
import com.example.ctftreasuremapservice.security.CheckUserAuthNEW;
import com.example.ctftreasuremapservice.security.JwtProvider;
import com.example.ctftreasuremapservice.services.AuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AuthenticateControllers {
    private final JwtProvider jwtProvider;
    private final LocationRepository locationRepository;

    private final CheckUserAuthNEW checkUserAuthNEW;

    private final AuthenticationService authenticationService;
    private final JwtRepo jwtRepo;

    private final TestRepo testRepo;

    public AuthenticateControllers(AuthenticationService authenticationService,
                                   JwtProvider jwtProvider, LocationRepository locationRepository, CheckUserAuthNEW checkUserAuthNEW, JwtRepo jwtRepo, TestRepo testRepo) {
        this.authenticationService = authenticationService;
        this.jwtProvider = jwtProvider;
        this.locationRepository = locationRepository;
        this.checkUserAuthNEW = checkUserAuthNEW;
        this.jwtRepo = jwtRepo;
        this.testRepo = testRepo;
    }

    @GetMapping("/auth-page")
    public ModelAndView getAuthForm() {
        return new ModelAndView("authorization-page.html");
    }



    // TODO: Вывод имени польщователя (Чужак)
    // TODO: Вывод информации на локацию
    // TODO: Реализовать XSS
    // TODO: Придумать третью уязвимость
    // TODO:
    @PostMapping("/login")
    public ModelAndView auth(@RequestBody UserDto userDto) {
        User user = userDto.fromDto(userDto);
        ModelAndView modelAndView = new ModelAndView("main-page-authorized.html");
        Authentication authentication = checkUserAuthNEW.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),
                user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return modelAndView;
    }


    @GetMapping("/main-page")
    public ModelAndView getMainPage() {
        ModelAndView modelAndView = new ModelAndView("main-page-authorized.html");
        modelAndView.addObject("containers", List.of("2", "5", "7",
                "19", "11"));
        modelAndView.addObject("locations", locationRepository.getAll());
        return modelAndView;
    }

}
