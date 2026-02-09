package com.epitech.pictmanager.modules.auth.application.usecases;

import com.epitech.pictmanager.modules.auth.application.dto.command.RegisterCommand;
import com.epitech.pictmanager.modules.auth.application.services.AuthService;
import com.epitech.pictmanager.modules.auth.domain.UserDomain;
import com.epitech.pictmanager.shared.contracts.command.CreateProfileCommand;
import com.epitech.pictmanager.shared.contracts.usecases.CreateProfileUseCasePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class RegisterUseCase {
    private final AuthService authService;
    private final CreateProfileUseCasePort createProfilUseCase;
    public RegisterUseCase(AuthService authService, CreateProfileUseCasePort createProfilUseCase) {
        this.authService = authService;
        this.createProfilUseCase = createProfilUseCase;
    }
    @Transactional
    public void execute(RegisterCommand command) {
        UserDomain user = this.authService.register(command);
        CreateProfileCommand profilCommand = new CreateProfileCommand(
            command.description(), command.coverPicture(), command.profilePicture(), user.getUserId()
        );
        this.createProfilUseCase.execute(profilCommand);
    }
}
