package com.epitech.pictmanager.modules.auth.application.usecases;

import com.epitech.pictmanager.modules.auth.application.command.RegisterCommand;
import com.epitech.pictmanager.modules.auth.application.services.AuthService;
import org.springframework.stereotype.Component;

@Component
public class RegisterUseCase {
    private final AuthService authService;
    public RegisterUseCase(AuthService authService) {
        this.authService = authService;
    }
    public void execute(RegisterCommand command) {
        this.authService.register(command);
    }
}
