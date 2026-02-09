package com.epitech.pictmanager.modules.auth.application.usecases;

import com.epitech.pictmanager.modules.auth.application.dto.command.LoginCommand;
import com.epitech.pictmanager.modules.auth.application.dto.read.TokensReadModel;
import com.epitech.pictmanager.modules.auth.application.services.AuthService;
import org.springframework.stereotype.Component;

@Component
public class LoginUseCase {
    private final AuthService authService;

    public LoginUseCase(AuthService authService) {
        this.authService = authService;
    }

    public TokensReadModel execute(LoginCommand command) {
        return this.authService.login(command.username(), command.password());
    }
}
