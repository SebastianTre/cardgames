package de.sebtre.cardgames.controller;

import de.sebtre.cardgames.dto.LoginRequest;
import de.sebtre.cardgames.dto.LoginResponse;
import de.sebtre.cardgames.dto.RegistrationRequest;
import de.sebtre.cardgames.dto.UserResponse;
import de.sebtre.cardgames.service.AppUserService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class  AuthController {
    private final AppUserService appUserService;

    public AuthController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegistrationRequest request) {

        return appUserService.registerUser(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {

        return appUserService.login(request);
    }

    @GetMapping("/test")
    public String test() {
        return "API erreichbar";
    }
}
