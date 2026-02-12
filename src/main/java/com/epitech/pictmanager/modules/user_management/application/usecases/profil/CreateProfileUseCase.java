package com.epitech.pictmanager.modules.user_management.application.usecases.profil;

import com.epitech.pictmanager.modules.user_management.domain.UserProfileDomain;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.ProfileRepositoryPort;
import com.epitech.pictmanager.shared.contracts.command.CreateProfileCommand;
import com.epitech.pictmanager.shared.contracts.repositories.UserLookUpRepositoryPort;
import com.epitech.pictmanager.shared.contracts.usecases.CreateProfileUseCasePort;
import org.springframework.stereotype.Component;


@Component
public class CreateProfileUseCase implements CreateProfileUseCasePort {

    private final ProfileRepositoryPort profileRepository;
    private final UserLookUpRepositoryPort userLookUpRepository;

    public CreateProfileUseCase(ProfileRepositoryPort profilRepository, UserLookUpRepositoryPort userLookUpRepository) {
        this.profileRepository = profilRepository;
        this.userLookUpRepository = userLookUpRepository;
    }

    @Override
    public void execute(CreateProfileCommand command) {
        Long id = this.userLookUpRepository.getUserIdWithPublicId(command.publicId());
        this.profileRepository.createProfile(
                id,
                new UserProfileDomain(
                        command.publicId(),
                        command.description(),
                        command.coverPicture(),
                        command.picture()
                )
        );
    }
}
