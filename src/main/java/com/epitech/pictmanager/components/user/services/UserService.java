package com.epitech.pictmanager.components.user.services;

import com.epitech.pictmanager.components.auth.services.PasswordEncryptionService;
import com.epitech.pictmanager.components.user.dto.UpdateProfilDto;
import com.epitech.pictmanager.components.user.repositories.ProfilJpaRepository;
import com.epitech.pictmanager.components.user.repositories.UserJpaRepository;
import com.epitech.pictmanager.models.Profil;
import com.epitech.pictmanager.models.User;
import com.epitech.pictmanager.responses.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserJpaRepository userRepository;
    private ProfilJpaRepository profileRepository;

    private PasswordEncryptionService passwordEncryptionService;
    @Autowired
    public UserService(UserJpaRepository userRepository, ProfilJpaRepository profileRepository, PasswordEncryptionService passwordEncryptionService) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncryptionService = passwordEncryptionService;
    }

    public ResponseEntity<GenericResponse> deleteUserAndProfil(String username) {
        try {
            User user = this.userRepository.findUserByUsername(username);
            Profil profile = this.profileRepository.findProfileByUser(user.getId());
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            this.profileRepository.delete(profile);
            this.userRepository.delete(user);
            return new ResponseEntity<GenericResponse>(new GenericResponse("User deleted successfully", HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity<GenericResponse> updateUserPassword(String username, String password) {
        try {
            User user = this.userRepository.findUserByUsername(username);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            user.setPassword(passwordEncryptionService.encrypt(password));
            this.userRepository.save(user);
            return new ResponseEntity<GenericResponse>(new GenericResponse("Password updated successfully", HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity<GenericResponse> updateUserEmail(String username, String email) {
        try {
            User user = this.userRepository.findUserByUsername(username);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            user.setEmail(email);
            this.userRepository.save(user);
            return new ResponseEntity<GenericResponse>(new GenericResponse("Email updated successfully", HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity<GenericResponse> updateUserProfil(String username, UpdateProfilDto updateProfilDto) {
        try {
            User user = this.userRepository.findUserByUsername(username);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            Profil profil = this.profileRepository.findProfileByUser(user.getId());
            if (profil == null) {
                throw new RuntimeException("Profile not found");
            }
            profil.setDescription(updateProfilDto.getDescription());
            profil.setProfilePicture(updateProfilDto.getProfilePicture());
            profil.setCoverPicture(updateProfilDto.getCoverPicture());
            this.profileRepository.save(profil);
            return new ResponseEntity<GenericResponse>(new GenericResponse("Profile updated successfully", HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }



}
