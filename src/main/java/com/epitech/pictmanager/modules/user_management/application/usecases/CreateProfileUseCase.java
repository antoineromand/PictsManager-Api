package com.epitech.pictmanager.modules.user_management.application.usecases;

import com.epitech.pictmanager.modules.user_management.domain.UserProfileDomain;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.ProfileRepositoryPort;
import com.epitech.pictmanager.shared.contracts.command.CreateProfileCommand;
import com.epitech.pictmanager.shared.contracts.usecases.CreateProfileUseCasePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;


@Component
public class CreateProfileUseCase implements CreateProfileUseCasePort {

    private final ProfileRepositoryPort profileRepository;
    public CreateProfileUseCase(ProfileRepositoryPort profilRepository) {
        this.profileRepository = profilRepository;
    }

    @Override
    public void execute(CreateProfileCommand command) {
        this.profileRepository.createProfile(
                new UserProfileDomain(
                        command.publicId(),
                        command.description(),
                        command.coverPicture(),
                        command.picture()
                )
        );
    }
}
