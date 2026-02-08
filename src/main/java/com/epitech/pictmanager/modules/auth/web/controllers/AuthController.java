package com.epitech.pictmanager.modules.auth.web.controllers;

import com.epitech.pictmanager.modules.auth.application.dto.read.TokensReadModel;
import com.epitech.pictmanager.modules.auth.application.usecases.LoginUseCase;
import com.epitech.pictmanager.modules.auth.application.usecases.RefreshTokenUseCase;
import com.epitech.pictmanager.modules.auth.application.usecases.RegisterUseCase;
import com.epitech.pictmanager.modules.auth.web.dto.LoginDto;
import com.epitech.pictmanager.modules.auth.web.dto.RegisterDto;
import com.epitech.pictmanager.shared.responses.GenericResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;


@RestController()
@RequestMapping("public/api/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;
    private final RegisterUseCase registerUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;

    public AuthController(LoginUseCase loginUseCase, RegisterUseCase registerUseCase, RefreshTokenUseCase refreshTokenUseCase) {
        this.loginUseCase = loginUseCase;
        this.registerUseCase = registerUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
    }

    @PostMapping("/register")
    public GenericResponse<Void> register(@Valid @RequestBody() RegisterDto registerDto) {
        this.registerUseCase.execute(registerDto.toCommand());
        return new GenericResponse<>("User registered successfully.", HttpStatus.CREATED.value(), null);
    }

    @PostMapping("/login")
    public GenericResponse<Void> login(@Valid @RequestBody() LoginDto loginDto, HttpServletResponse response) {
        TokensReadModel result = this.loginUseCase.execute(loginDto.toCommand());
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + result.accessToken());
        ResponseCookie responseCookie = ResponseCookie.from("refresh_token", result.refreshToken())
                .httpOnly(true)
                .secure(true)
                .sameSite("Lax")
                .path("/public/api/auth/refresh")
                .maxAge(Duration.ofDays(7))
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
        return new GenericResponse<>("User logged in successfully", HttpStatus.OK.value(), null);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Object> refresh(@CookieValue(name = "refresh_token", required = false) String refreshToken) {
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String newAccessToken = this.refreshTokenUseCase.execute(refreshToken);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + newAccessToken)
                .build();
    }

    @PostMapping("/logout")
    public GenericResponse<Void> logout(HttpServletResponse response) {

        ResponseCookie deleteRefresh = ResponseCookie.from("refresh_token", "")
                .httpOnly(true)
                .secure(true)
                .sameSite("Lax")
                .path("/public/api/auth/refresh")
                .maxAge(0)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, deleteRefresh.toString());

        return new GenericResponse<>("User logged out successfully", HttpStatus.OK.value(), null);
    }
}
