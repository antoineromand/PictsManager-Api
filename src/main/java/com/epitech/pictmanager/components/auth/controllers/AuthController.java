package com.epitech.pictmanager.components.auth.controllers;

import com.epitech.pictmanager.components.auth.dto.LoginDto;
import com.epitech.pictmanager.components.auth.dto.RegisterDto;
import com.epitech.pictmanager.components.auth.services.AuthService;
import com.epitech.pictmanager.responses.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController()
@RequestMapping("public/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @GetMapping()
    public String index() {
        return "Auth controller is working ...";
    }

    @PostMapping("/register")
    public ResponseEntity<GenericResponse> register(@RequestBody() RegisterDto registerDto) {
        return this.authService.register(registerDto);
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse> login(@RequestBody() LoginDto loginDto, HttpServletResponse response) {
        return this.authService.login(loginDto.getUsername(), loginDto.getPassword(), response);
    }
}
