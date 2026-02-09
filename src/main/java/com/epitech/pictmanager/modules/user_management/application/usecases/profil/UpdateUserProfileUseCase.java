package com.epitech.pictmanager.modules.user_management.application.usecases.profil;

import com.epitech.pictmanager.modules.user_management.domain.UserProfileDomain;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.ProfileRepositoryPort;
import com.epitech.pictmanager.modules.user_management.web.dto.response.UpdateProfilResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserProfileUseCase {
    private final ProfileRepositoryPort profileRepositoryPort;

    public UpdateUserProfileUseCase(ProfileRepositoryPort profileRepositoryPort) {
        this.profileRepositoryPort = profileRepositoryPort;
    }

    public UpdateProfilResponseDTO execute(UserProfileDomain domain) {
        UserProfileDomain user = this.profileRepositoryPort.updateUserProfile(domain);

        return new UpdateProfilResponseDTO(
                user.getDescription(),
                user.getPicture(),
                user.getCoverPicture()
        );
    }
}
