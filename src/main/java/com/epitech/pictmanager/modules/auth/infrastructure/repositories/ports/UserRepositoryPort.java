package com.epitech.pictmanager.modules.auth.infrastructure.repositories.ports;

import com.epitech.pictmanager.modules.auth.domain.UserDomain;

import java.util.UUID;

public interface UserRepositoryPort {
    UserDomain getUserByUsername(String username);
    UserDomain getUserByPublicId(UUID publicId);
    UserDomain createUser(UserDomain userDomain);
    void deleteUser(UUID publicId);
    UserDomain updateUser(UserDomain userDomain);
}
