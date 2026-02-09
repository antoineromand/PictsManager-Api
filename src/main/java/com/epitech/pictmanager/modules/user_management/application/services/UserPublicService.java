package com.epitech.pictmanager.modules.user_management.application.services;

import com.epitech.pictmanager.modules.auth.domain.UserDomain;
import com.epitech.pictmanager.modules.auth.infrastructure.repositories.ports.UserRepositoryPort;
import com.epitech.pictmanager.modules.user_management.application.dto.UserSearchResponse;
import com.epitech.pictmanager.modules.user_management.application.dto.UserWithoutPasswordDto;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.jpa.ProfileJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class UserPublicService {
    private final UserRepositoryPort userRepository;
    private final ProfileJpaRepository profileRepository;

    public UserPublicService(UserRepositoryPort userRepository, ProfileJpaRepository profileRepository) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }


    public ResponseEntity<Object> search(String username) {
//        List<UserSearchResponse> response = userRepository.findByUsernameLike(username);
        List<UserSearchResponse> response = new ArrayList<>();
        if (response.isEmpty()) {
            return new ResponseEntity<Object>("No users found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<Object> getUser(String username) {
        UserDomain user = this.userRepository.getUserByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found with username: " + username)
        );

        UserWithoutPasswordDto userWithoutPasswordDto = new UserWithoutPasswordDto(user.getUsername(), user.getEmail(), user.getBirthDate(), user.isPublic());

        return ResponseEntity.ok(userWithoutPasswordDto);
    }
}
