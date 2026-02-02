package com.epitech.pictmanager.modules.auth.infrastructure.repositories.jpa;

import com.epitech.pictmanager.modules.auth.infrastructure.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByPublicId(UUID publicId);

}
