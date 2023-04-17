package com.epitech.pictmanager.components.user.services;

import com.epitech.pictmanager.components.user.dto.UserSearchResponse;
import com.epitech.pictmanager.components.user.dto.UserWithoutPasswordDto;
import com.epitech.pictmanager.components.user.repositories.ProfilJpaRepository;
import com.epitech.pictmanager.components.user.repositories.UserJpaRepository;
import com.epitech.pictmanager.models.Profil;
import com.epitech.pictmanager.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class UserPublicService {
    @Autowired
    UserJpaRepository userRepository;
    @Autowired
    ProfilJpaRepository profilRepository;


    public ResponseEntity<Object> search(String username) {
        List<UserSearchResponse> response = userRepository.findByUsernameLike(username);
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
        UserWithoutPasswordDto userWithoutPasswordDto = new UserWithoutPasswordDto(user.getUsername(), user.getEmail(), user.getDateOfBirth(), user.getPublic(), user.getProfile(), user.getImage());

        return ResponseEntity.ok(userWithoutPasswordDto);
    }
}
