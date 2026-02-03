package com.epitech.pictmanager.modules.user_management.application.usecases;

import com.epitech.pictmanager.modules.user_management.domain.UserProfilDomain;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.ProfilRepositoryPort;
import com.epitech.pictmanager.shared.contracts.command.CreateProfilCommand;
import com.epitech.pictmanager.shared.contracts.usecases.CreateProfilUseCasePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;


@Component
public class CreateProfilUseCase implements CreateProfilUseCasePort {

    private final ProfilRepositoryPort profilRepository;
    public CreateProfilUseCase(ProfilRepositoryPort profilRepository) {
        this.profilRepository = profilRepository;
    }

    @Override
    public void execute(CreateProfilCommand command) {
        this.profilRepository.createProfil(
                new UserProfilDomain(
                        command.publicId(),
                        command.description(),
                        command.coverPicture(),
                        command.picture()
                )
        );
    }
}
