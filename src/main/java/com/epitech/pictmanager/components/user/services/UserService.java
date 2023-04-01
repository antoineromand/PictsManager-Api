package com.epitech.pictmanager.components.user.services;

import com.epitech.pictmanager.components.auth.services.PasswordEncryptionService;
import com.epitech.pictmanager.components.user.dto.UpdateProfilDto;
import com.epitech.pictmanager.components.user.dto.UpdateSecurityDto;
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

    public ResponseEntity<GenericResponse> deleteUserAndProfil(String id) {
        try {
            User user = this.userRepository.findUserById(Long.parseLong(id));
            Profil profile = this.profileRepository.findProfileByUser(user);
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

    public ResponseEntity<GenericResponse> updateUserProfil(String username, UpdateProfilDto updateProfilDto) {
        try {
            User user = this.userRepository.findUserByUsername(username);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            Profil profil = this.profileRepository.findProfileByUser(user);
            if (profil == null) {
                throw new RuntimeException("Profile not found");
            }
            boolean hasChanged = false;
            if (updateProfilDto.getDescription() != null && !updateProfilDto.getDescription().equals(profil.getDescription())) {
                profil.setDescription(updateProfilDto.getDescription());
                hasChanged = true;
            }
            if (updateProfilDto.getProfilePicture() != null && !updateProfilDto.getProfilePicture().equals(profil.getProfilePicture())) {
                profil.setProfilePicture(updateProfilDto.getProfilePicture());
                hasChanged = true;
            }
            if (updateProfilDto.getCoverPicture() != null && !updateProfilDto.getCoverPicture().equals(profil.getCoverPicture())) {
                profil.setCoverPicture(updateProfilDto.getCoverPicture());
                hasChanged = true;
            }
            if (!hasChanged) {
                return new ResponseEntity<GenericResponse>(new GenericResponse("No changes detected, profile not updated", HttpStatus.NOT_MODIFIED.value()), HttpStatus.NOT_MODIFIED);
            }
            this.profileRepository.save(profil);
            return new ResponseEntity<GenericResponse>(new GenericResponse("Profile updated successfully", HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity<GenericResponse> updateUserSecurity(String username, UpdateSecurityDto updateSecurityDto) {
        try {
            User user = this.userRepository.findUserByUsername(username);
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            boolean hasChanged = false;

            return new ResponseEntity<GenericResponse>(new GenericResponse("Profile updated successfully", HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
