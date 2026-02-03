package com.epitech.pictmanager.modules.auth.application.usecases;

import com.epitech.pictmanager.modules.auth.application.command.RegisterCommand;
import com.epitech.pictmanager.modules.auth.application.services.AuthService;
import com.epitech.pictmanager.modules.auth.domain.UserDomain;
import com.epitech.pictmanager.shared.contracts.command.CreateProfilCommand;
import com.epitech.pictmanager.shared.contracts.usecases.CreateProfilUseCasePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class RegisterUseCase {
    private final AuthService authService;
    private final CreateProfilUseCasePort createProfilUseCase;
    public RegisterUseCase(AuthService authService, CreateProfilUseCasePort createProfilUseCase) {
        this.authService = authService;
        this.createProfilUseCase = createProfilUseCase;
    }
    @Transactional
    public void execute(RegisterCommand command) {
        UserDomain user = this.authService.register(command);
        System.out.println(user.getUserId());
        CreateProfilCommand profilCommand = new CreateProfilCommand(
            command.description(), command.coverPicture(), command.profilePicture(), user.getUserId()
        );
        this.createProfilUseCase.execute(profilCommand);
    }
}
