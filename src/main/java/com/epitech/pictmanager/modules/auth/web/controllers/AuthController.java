package com.epitech.pictmanager.modules.auth.web.controllers;

import com.epitech.pictmanager.modules.auth.application.usecases.LoginUseCase;
import com.epitech.pictmanager.modules.auth.application.usecases.RegisterUseCase;
import com.epitech.pictmanager.modules.auth.web.dto.LoginDto;
import com.epitech.pictmanager.modules.auth.web.dto.RegisterDto;
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

    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;

    public AuthController(LoginUseCase loginUseCase, RegisterUseCase registerUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUseCase = registerUseCase;
    }

    @PostMapping("/register")
    public GenericResponse<Void> register(@Valid @RequestBody() RegisterDto registerDto) {
        this.registerUseCase.execute(registerDto.toCommand());
        return new GenericResponse<>("User registered successfully.", HttpStatus.CREATED.value(), null);
    }

    @PostMapping("/login")
    public GenericResponse<Void> login(@Valid @RequestBody() LoginDto loginDto, HttpServletResponse response) {
        String token = this.loginUseCase.execute(loginDto.toCommand());
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        return new GenericResponse<>("User logged in successfully", HttpStatus.OK.value(), null);
    }
}
