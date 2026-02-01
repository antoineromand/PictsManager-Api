package com.epitech.pictmanager.modules.user.services;

import com.epitech.pictmanager.modules.user.dto.UserSearchResponse;
import com.epitech.pictmanager.modules.user.dto.UserWithoutPasswordDto;
import com.epitech.pictmanager.modules.user.repositories.ProfilJpaRepository;
import com.epitech.pictmanager.modules.auth.infrastructure.repositories.UserJpaRepository;
import com.epitech.pictmanager.modules.auth.infrastructure.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service()
public class UserPublicService {
    private final UserJpaRepository userRepository;
    private final ProfilJpaRepository profilRepository;

    public UserPublicService(UserJpaRepository userRepository, ProfilJpaRepository profilRepository) {
        this.userRepository = userRepository;
        this.profilRepository = profilRepository;
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
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return new ResponseEntity<Object>("No user found", HttpStatus.NOT_FOUND);
        }
        UserWithoutPasswordDto userWithoutPasswordDto = new UserWithoutPasswordDto(user.getUsername(), user.getEmail(), user.getDateOfBirth(), user.getIsPublic());

        return ResponseEntity.ok(userWithoutPasswordDto);
    }
}
