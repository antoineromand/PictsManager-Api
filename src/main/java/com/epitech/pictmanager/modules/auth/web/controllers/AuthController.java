package com.epitech.pictmanager.modules.auth.web.controllers;

import com.epitech.pictmanager.modules.auth.application.dto.LoginDto;
import com.epitech.pictmanager.modules.auth.application.dto.RegisterDto;
import com.epitech.pictmanager.modules.auth.application.services.AuthService;
import com.epitech.pictmanager.shared.responses.GenericResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("public/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public GenericResponse<Void> register(@Valid @RequestBody() RegisterDto registerDto) {
        this.authService.register(registerDto);
        return new GenericResponse<>("User registered successfully.", HttpStatus.CREATED.value(), null);
    }

    @PostMapping("/login")
    public GenericResponse<Void> login(@Valid @RequestBody() LoginDto loginDto, HttpServletResponse response) {
        String token = this.authService.login(loginDto.getUsername(), loginDto.getPassword(), response);
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return new GenericResponse<>("User logged in successfully", HttpStatus.OK.value(), null);
    }
}
