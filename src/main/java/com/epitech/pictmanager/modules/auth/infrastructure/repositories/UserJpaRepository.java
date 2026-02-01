package com.epitech.pictmanager.modules.auth.infrastructure.repositories;

import com.epitech.pictmanager.modules.auth.infrastructure.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    Boolean existsUserByUsername(String username);

    Boolean existsUserByEmail(String email);

    User findUserByUsername(String username);

    User findUserById(Long id);

}
