package com.epitech.pictmanager.modules.auth.application.usecases;

import com.epitech.pictmanager.modules.auth.application.dto.read.TokensReadModel;
import com.epitech.pictmanager.modules.auth.application.exceptions.InvalidRefreshTokenException;
import com.epitech.pictmanager.modules.auth.application.services.AuthService;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenUseCase {
    private final AuthService authService;

    public RefreshTokenUseCase(AuthService authService) {
        this.authService = authService;
    }

    public String execute(String refreshToken) {
        try {
            return this.authService.refreshToken(refreshToken);
        } catch (InvalidRefreshTokenException e) {
            throw new InvalidRefreshTokenException();
        }
    }
}
