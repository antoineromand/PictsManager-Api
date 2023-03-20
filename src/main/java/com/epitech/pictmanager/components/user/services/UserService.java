package com.epitech.pictmanager.components.user.services;

import com.epitech.pictmanager.components.auth.services.PasswordEncryptionService;
import com.epitech.pictmanager.components.user.repositories.ProfileJpaRepository;
import com.epitech.pictmanager.components.user.repositories.UserJpaRepository;
import com.epitech.pictmanager.models.Profile;
import com.epitech.pictmanager.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserJpaRepository userRepository;
    private ProfileJpaRepository profileRepository;

    private PasswordEncryptionService passwordEncryptionService;
    @Autowired
    public UserService(UserJpaRepository userRepository, ProfileJpaRepository profileRepository, PasswordEncryptionService passwordEncryptionService) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.passwordEncryptionService = passwordEncryptionService;
    }

    public void deleteUserAndProfil(String username) {
        User user = this.userRepository.findUserByUsername(username);
        Profile profile = this.profileRepository.findProfileByUser(user.getId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        this.profileRepository.delete(profile);
        this.userRepository.delete(user);
    }

    public void updateUserPassword(String username, String password) {
        User user = this.userRepository.findUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setPassword(passwordEncryptionService.encrypt(password));
        this.userRepository.save(user);
    }

    public void updateUserEmail(String username, String email) {
        User user = this.userRepository.findUserByUsername(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setEmail(email);
        this.userRepository.save(user);
    }



}
