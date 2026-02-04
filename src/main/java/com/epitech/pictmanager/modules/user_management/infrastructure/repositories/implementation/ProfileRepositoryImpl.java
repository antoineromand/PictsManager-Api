package com.epitech.pictmanager.modules.user_management.infrastructure.repositories.implementation;

import com.epitech.pictmanager.modules.user_management.application.dto.read.UserProfileReadModel;
import com.epitech.pictmanager.modules.user_management.domain.UserProfileDomain;
import com.epitech.pictmanager.modules.user_management.infrastructure.models.UserProfile;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.jpa.ProfileJpaRepository;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.ProfileRepositoryPort;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.UserLookUpRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class ProfileRepositoryImpl implements ProfileRepositoryPort {
    private final UserLookUpRepositoryPort userLookUpRepositoryPort;
    private final ProfileJpaRepository jpaRepository;
    public ProfileRepositoryImpl(UserLookUpRepositoryPort userLookUpRepositoryPort, ProfileJpaRepository jpaRepository) {
        this.userLookUpRepositoryPort = userLookUpRepositoryPort;
        this.jpaRepository = jpaRepository;
    }
    @Override
    public void createProfile(UserProfileDomain domain) {
        Long id = this.userLookUpRepositoryPort.getUserIdWithPublicId(domain.getPublicId());
        UserProfile userProfile = UserProfile.toEntity(domain, id);
        this.jpaRepository.save(userProfile);
    }

    @Override
    public Optional<UserProfileReadModel> getProfile(String userId) {
        return this.userLookUpRepositoryPort.getUserAndProfilWithPublicId(userId);
    }
}
