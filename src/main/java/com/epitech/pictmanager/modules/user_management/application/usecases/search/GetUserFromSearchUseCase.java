package com.epitech.pictmanager.modules.user_management.application.usecases.search;

import com.epitech.pictmanager.modules.user_management.application.exceptions.PublicUserProfilNotFoundException;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.ProfileRepositoryPort;
import com.epitech.pictmanager.modules.user_management.web.dto.view.UserProfileDetailView;
import org.springframework.stereotype.Service;

@Service
public class GetUserFromSearchUseCase {
    private final ProfileRepositoryPort profileRepositoryPort;
    public GetUserFromSearchUseCase(ProfileRepositoryPort profileRepositoryPort) {
        this.profileRepositoryPort = profileRepositoryPort;
    }
    public UserProfileDetailView execute(String username) {
        return this.profileRepositoryPort.getUserPublicProfilWithUsername(username).map(
                result -> new UserProfileDetailView(
                        result.username(),
                        result.description(),
                        result.picture(),
                        result.coverPicture(),
                        result.isPublic()
                )
        ).orElseThrow(
                PublicUserProfilNotFoundException::new
        );
    }
}
