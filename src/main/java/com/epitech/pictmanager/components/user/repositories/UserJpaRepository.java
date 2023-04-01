package com.epitech.pictmanager.components.user.repositories;

import com.epitech.pictmanager.components.user.dto.UserWithoutPasswordAndProfilDTO;
import com.epitech.pictmanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;
public interface UserJpaRepository extends JpaRepository<User, Long> {
    Boolean existsUserByUsername(String username);

    Boolean existsUserByEmail(String email);

    User findUserByUsername(String username);

    User findUserById(Long id);

    @Query("SELECT new com.epitech.pictmanager.components.user.dto.UserWithoutPasswordAndProfilDTO(u.username, u.email, u.dateOfBirth, u.isPublic) FROM User u WHERE u.id = ?1")
    UserWithoutPasswordAndProfilDTO userById(Long id);
}
