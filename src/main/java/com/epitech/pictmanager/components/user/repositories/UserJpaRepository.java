package com.epitech.pictmanager.components.user.repositories;

import com.epitech.pictmanager.components.user.dto.UserSearchResponse;
import com.epitech.pictmanager.components.user.dto.UserWithoutPasswordAndProfilDTO;
import com.epitech.pictmanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;
public interface UserJpaRepository extends JpaRepository<User, Long> {
    Boolean existsUserByUsername(String username);

    Boolean existsUserByEmail(String email);

    User findUserByUsername(String username);

    User findUserById(Long id);

    @Query("SELECT NEW com.epitech.pictmanager.components.user.dto.UserSearchResponse(u.username, u.isPublic, p.profilePicture) FROM User u JOIN u.profile p WHERE u.username LIKE %:username%")
    List<UserSearchResponse> findByUsernameLike(@Param("username") String username);

    @Query("SELECT new com.epitech.pictmanager.components.user.dto.UserWithoutPasswordAndProfilDTO(u.username, u.email, u.dateOfBirth, u.isPublic) FROM User u WHERE u.id = ?1")
    UserWithoutPasswordAndProfilDTO userById(Long id);
}
