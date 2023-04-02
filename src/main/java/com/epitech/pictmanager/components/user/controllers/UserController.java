package com.epitech.pictmanager.components.user.controllers;

import com.epitech.pictmanager.components.user.dto.UpdateProfilDto;
import com.epitech.pictmanager.components.user.dto.UpdateSecurityDto;
import com.epitech.pictmanager.components.user.dto.UserWithoutPasswordAndProfilDTO;
import com.epitech.pictmanager.components.user.repositories.ProfilJpaRepository;
import com.epitech.pictmanager.components.user.repositories.UserJpaRepository;
import com.epitech.pictmanager.components.user.services.UserService;
import com.epitech.pictmanager.models.Profil;
import com.epitech.pictmanager.models.User;
import com.epitech.pictmanager.responses.GenericResponse;
import com.epitech.pictmanager.responses.GenericUpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("private/api/user")
public class UserController {
    @Autowired
    private UserJpaRepository userRepository;
    @Autowired
    private ProfilJpaRepository profileRepository;

    @Autowired
    private UserService userService;
    @GetMapping("/me/profil")
    public ResponseEntity<Profil> getProfil(@AuthenticationPrincipal String id) {
        User user = this.userRepository.findUserById(Long.parseLong(id));
        Profil profil = this.profileRepository.findProfileByUser(user);
        if (profil == null) {
            throw new RuntimeException("User not found");
        }
        return new ResponseEntity<>(profil, HttpStatus.OK);
    }

    @GetMapping("/me/security")
    public ResponseEntity<UserWithoutPasswordAndProfilDTO> getUser(@AuthenticationPrincipal String id) {
        UserWithoutPasswordAndProfilDTO user = this.userRepository.userById(Long.parseLong(id));
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/me/profil")
    public ResponseEntity<GenericUpdateResponse> updateProfil(@AuthenticationPrincipal String id, @RequestBody() UpdateProfilDto updateProfilDto) {
        return this.userService.updateUserProfil(id, updateProfilDto);
    }

    @PutMapping("/me/security")
    public ResponseEntity<GenericUpdateResponse> updateSecurity(@AuthenticationPrincipal String id, @RequestBody() UpdateSecurityDto updateSecurityDto) {
        return this.userService.updateUserSecurity(id, updateSecurityDto);
    }

    @DeleteMapping("/me")
    public ResponseEntity<GenericResponse> deleteUser(@AuthenticationPrincipal String id) {
        return this.userService.deleteUserAndProfil(id);
    }

}
