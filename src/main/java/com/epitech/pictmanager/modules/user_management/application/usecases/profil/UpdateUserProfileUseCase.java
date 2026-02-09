package com.epitech.pictmanager.modules.user_management.application.usecases.profil;

import com.epitech.pictmanager.modules.user_management.domain.UserProfileDomain;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.ProfileRepositoryPort;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.UserLookUpRepositoryPort;
import com.epitech.pictmanager.modules.user_management.web.dto.response.UpdateProfilResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class UpdateUserProfileUseCase {
    private final ProfileRepositoryPort profileRepositoryPort;
    private final UserLookUpRepositoryPort userLookUpRepositoryPort;

    public UpdateUserProfileUseCase(ProfileRepositoryPort profileRepositoryPort, UserLookUpRepositoryPort userLookUpRepositoryPort) {
        this.profileRepositoryPort = profileRepositoryPort;
        this.userLookUpRepositoryPort = userLookUpRepositoryPort;
    }

    public UpdateProfilResponseDTO execute(UserProfileDomain domain) {
        Long id = this.userLookUpRepositoryPort.getUserIdWithPublicId(domain.getPublicId());
        UserProfileDomain user = this.profileRepositoryPort.updateUserProfile(id, domain);

        return new UpdateProfilResponseDTO(
                user.getDescription(),
                user.getPicture(),
                user.getCoverPicture()
        );
    }
}
