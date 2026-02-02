package com.epitech.pictmanager.modules.auth.infrastructure.repositories.implementation;

import com.epitech.pictmanager.modules.auth.domain.UserDomain;
import com.epitech.pictmanager.modules.auth.infrastructure.models.User;
import com.epitech.pictmanager.modules.auth.infrastructure.repositories.jpa.UserJpaRepository;
import com.epitech.pictmanager.modules.auth.infrastructure.repositories.ports.UserRepositoryPort;
import com.epitech.pictmanager.shared.SqlExceptionCodeGlobal;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.UUID;

@Component
public class UserRepositoryImpl implements UserRepositoryPort {
    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }


    @Override
    public UserDomain getUserByUsername(String username) {
        User userEntity = this.userJpaRepository.findUserByUsername(username).orElseThrow(
                () -> new EntityNotFoundException("User not found with username: " + username)
        );
        return userEntity.toDomain();
    }

    @Override
    public UserDomain getUserByPublicId(UUID publicId) {
        User userEntity = this.userJpaRepository.findUserByPublicId(publicId).orElseThrow(
                () -> new EntityNotFoundException("User not found with public id: " + publicId)
        );
        return userEntity.toDomain();
    }

    @Override
    public UserDomain createUser(UserDomain userDomain) {
        try {
            User user = User.fromDomain(userDomain, null);
            this.userJpaRepository.save(user);
            return user.toDomain();
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Username already taken",
                    e
            );
        }
    }

    @Override
    public void deleteUser(UUID publicId) {
        User userEntity = this.userJpaRepository.findUserByPublicId(publicId).orElseThrow(
                () -> new EntityNotFoundException("User not found with public id: " + publicId)
        );
        this.userJpaRepository.delete(userEntity);
    }

    @Override
    public UserDomain updateUser(UserDomain userDomain) {
        return null;
    }


}
