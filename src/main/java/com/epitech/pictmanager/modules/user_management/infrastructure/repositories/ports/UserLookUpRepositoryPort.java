package com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports;

import com.epitech.pictmanager.modules.user_management.application.dto.read.UserProfileReadModel;
import com.epitech.pictmanager.modules.user_management.application.dto.read.UserProfileSearchReadModel;

import java.util.List;
import java.util.Optional;

public interface UserLookUpRepositoryPort {
    Long getUserIdWithPublicId(String publicId);
}
