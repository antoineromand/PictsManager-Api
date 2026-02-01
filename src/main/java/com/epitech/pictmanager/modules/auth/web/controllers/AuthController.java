package com.epitech.pictmanager.modules.auth.web.controllers;

import com.epitech.pictmanager.modules.auth.dto.LoginDto;
import com.epitech.pictmanager.modules.auth.dto.RegisterDto;
import com.epitech.pictmanager.modules.auth.application.services.AuthService;
import com.epitech.pictmanager.shared.responses.GenericResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("public/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> register(@Valid @RequestBody() RegisterDto registerDto) {
        System.out.println("RAW BODY = " + registerDto.getEmail());
        return this.authService.register(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse> login(@Valid @RequestBody() LoginDto loginDto, HttpServletResponse response) {
        return this.authService.login(loginDto.getUsername(), loginDto.getPassword(), response);
    }
}
