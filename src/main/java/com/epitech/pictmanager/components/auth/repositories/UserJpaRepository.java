package com.epitech.pictmanager.components.auth.repositories;

import com.epitech.pictmanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
public interface UserJpaRepository extends JpaRepository<User, Long> {
    Boolean existsUserByUsername(String username);

    Boolean existsUserByEmail(String email);

    User findUserByUsername(String username);
}
