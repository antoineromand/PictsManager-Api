package com.epitech.pictmanager.components.user.services;

import com.epitech.pictmanager.components.auth.services.PasswordEncryptionService;
import com.epitech.pictmanager.components.user.dto.UpdateProfilDto;
import com.epitech.pictmanager.components.user.dto.UpdateSecurityDto;
import com.epitech.pictmanager.components.user.repositories.ProfilJpaRepository;
import com.epitech.pictmanager.components.user.repositories.UserJpaRepository;
import com.epitech.pictmanager.models.Profil;
import com.epitech.pictmanager.models.User;
import com.epitech.pictmanager.responses.GenericResponse;
import com.epitech.pictmanager.responses.GenericUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            Profil profile = this.profileRepository.findProfileByUser(user);
            if(profile == null) {
                throw new RuntimeException("Profile not found");
            }
            this.profileRepository.delete(profile);
            this.userRepository.delete(user);
            return new ResponseEntity<GenericResponse>(new GenericResponse("User deleted successfully", HttpStatus.ACCEPTED.value()), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity<GenericUpdateResponse> updateUserProfil(String id, UpdateProfilDto updateProfilDto) {
        try {
            User user = this.userRepository.findUserById(Long.parseLong(id));
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            Profil profil = this.profileRepository.findProfileByUser(user);
            if (profil == null) {
                throw new RuntimeException("Profile not found");
            }
            List<String> updatedFields = new ArrayList<>();
            boolean hasChanged = false;
            if (updateProfilDto.getDescription() != null && !updateProfilDto.getDescription().equals(profil.getDescription())) {
                profil.setDescription(updateProfilDto.getDescription());
                updatedFields.add("description");
                hasChanged = true;
            }
            if (updateProfilDto.getProfilePicture() != null && !updateProfilDto.getProfilePicture().equals(profil.getProfilePicture())) {
                profil.setProfilePicture(updateProfilDto.getProfilePicture());
                updatedFields.add("profilePicture");
                hasChanged = true;
            }
            if (updateProfilDto.getCoverPicture() != null && !updateProfilDto.getCoverPicture().equals(profil.getCoverPicture())) {
                profil.setCoverPicture(updateProfilDto.getCoverPicture());
                updatedFields.add("coverPicture");
                hasChanged = true;
            }
            if (!hasChanged) {
                return new ResponseEntity<GenericUpdateResponse>(new GenericUpdateResponse("No changes detected, profile not updated", HttpStatus.NOT_MODIFIED.value(), updatedFields), HttpStatus.NOT_MODIFIED);
            }
            this.profileRepository.save(profil);
            return new ResponseEntity<GenericUpdateResponse>(new GenericUpdateResponse("Profile updated successfully", HttpStatus.ACCEPTED.value(), updatedFields), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public ResponseEntity<GenericUpdateResponse> updateUserSecurity(String id, UpdateSecurityDto updateSecurityDto) {
        try {
            User user = this.userRepository.findUserById(Long.parseLong(id));
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            boolean hasChanged = false;
            List<String> updatedFields = new ArrayList<>();
            if (updateSecurityDto.getUsername() != null && !user.getUsername().equals(updateSecurityDto.getUsername())) {
                if(this.userRepository.existsUserByUsername(updateSecurityDto.getUsername())) {
                    throw new RuntimeException("Username already taken");
                }
                user.setUsername(updateSecurityDto.getUsername());
                updatedFields.add("username");
                hasChanged = true;
            }
            if (updateSecurityDto.getEmail() != null && !user.getEmail().equals(updateSecurityDto.getEmail())) {
                if(this.userRepository.existsUserByEmail(updateSecurityDto.getEmail())) {
                    throw new RuntimeException("Email already taken");
                }
                user.setEmail(updateSecurityDto.getEmail());
                updatedFields.add("email");
                hasChanged = true;
            }
            if (updateSecurityDto.getVisibility() != null && !user.getPublic().equals(updateSecurityDto.getVisibility())) {
                user.setPublic(updateSecurityDto.getVisibility());
                updatedFields.add("isPublic");
                hasChanged = true;
            }
            if(updateSecurityDto.getPassword() != null && !passwordEncryptionService.check(updateSecurityDto.getPassword(), user.getPassword())) {
                user.setPassword(passwordEncryptionService.encrypt(updateSecurityDto.getPassword()));
                updatedFields.add("password");
                hasChanged = true;
            }
            if(!hasChanged) {
                return new ResponseEntity<GenericUpdateResponse>(new GenericUpdateResponse("No changes detected, profile not updated", HttpStatus.NOT_MODIFIED.value(), updatedFields), HttpStatus.NOT_MODIFIED);
            }
            this.userRepository.save(user);
            return new ResponseEntity<GenericUpdateResponse>(new GenericUpdateResponse("Profile updated successfully", HttpStatus.ACCEPTED.value(), updatedFields), HttpStatus.ACCEPTED);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
