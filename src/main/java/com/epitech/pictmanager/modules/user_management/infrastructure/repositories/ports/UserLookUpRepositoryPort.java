package com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports;

import java.util.UUID;

public interface UserLookUpRepositoryPort {
    Long getUserIdWithPublicId(String publicId);
}
