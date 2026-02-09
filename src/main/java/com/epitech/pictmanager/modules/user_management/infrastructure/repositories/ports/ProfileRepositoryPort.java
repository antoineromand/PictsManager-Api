package com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports;

import com.epitech.pictmanager.modules.user_management.application.dto.read.UserProfileReadModel;
import com.epitech.pictmanager.modules.user_management.application.dto.read.UserProfileSearchReadModel;
import com.epitech.pictmanager.modules.user_management.application.dto.read.UserPublicProfileReadModel;
import com.epitech.pictmanager.modules.user_management.domain.UserProfileDomain;

import java.util.List;
import java.util.Optional;

public interface ProfileRepositoryPort {
    void createProfile(UserProfileDomain profil);
    Optional<UserProfileReadModel> getUserAndProfilWithPublicId(String publicId);
    List<UserProfileSearchReadModel> getUsersAndProfilsWithSearchInput(String searchInput, int limit, int offset);
    int getTotalFromSearch(String searchInput);
    Optional<UserPublicProfileReadModel> getUserPublicProfilWithUsername(String username);

    UserProfileDomain updateUserProfile(UserProfileDomain userProfile);
}

