package com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports;

import com.epitech.pictmanager.modules.user_management.application.dto.read.UserProfileReadModel;
import com.epitech.pictmanager.modules.user_management.domain.UserProfileDomain;

import java.util.Optional;

public interface ProfileRepositoryPort {
    void createProfile(UserProfileDomain profil);
    Optional<UserProfileReadModel> getProfile(String userId);
}
