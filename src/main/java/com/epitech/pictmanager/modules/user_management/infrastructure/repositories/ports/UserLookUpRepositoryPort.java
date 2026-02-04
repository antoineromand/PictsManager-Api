package com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports;

import com.epitech.pictmanager.modules.user_management.application.dto.read.UserProfileReadModel;

import java.util.Optional;

public interface UserLookUpRepositoryPort {
    Long getUserIdWithPublicId(String publicId);
    Optional<UserProfileReadModel> getUserAndProfilWithPublicId(String publicId);
}
