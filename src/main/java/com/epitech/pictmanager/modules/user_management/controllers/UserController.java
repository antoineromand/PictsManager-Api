package com.epitech.pictmanager.modules.user_management.controllers;

import com.epitech.pictmanager.modules.user_management.dto.UpdateProfilDto;
import com.epitech.pictmanager.modules.user_management.dto.UpdateSecurityDto;
import com.epitech.pictmanager.modules.user_management.dto.UserWithoutPasswordAndProfilDTO;
import com.epitech.pictmanager.modules.user_management.repositories.ProfilJpaRepository;
import com.epitech.pictmanager.modules.auth.infrastructure.repositories.UserJpaRepository;
import com.epitech.pictmanager.modules.user_management.services.UserService;
import com.epitech.pictmanager.models.Profil;
import com.epitech.pictmanager.modules.auth.infrastructure.models.User;
import com.epitech.pictmanager.shared.responses.GenericResponse;
import com.epitech.pictmanager.shared.responses.GenericUpdateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("private/api/user")
public class UserController {
    private final UserJpaRepository userRepository;
    private final ProfilJpaRepository profileRepository;
    private final UserService userService;

    public UserController(UserJpaRepository userRepository, ProfilJpaRepository profileRepository, UserService userService) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.userService = userService;
    }

    @GetMapping("/me/profil")
    public ResponseEntity<Profil> getProfil(@AuthenticationPrincipal String id) {
        // call port from authentication/infra/repo/ports and not repository
        User user = this.userRepository.findUserById(Long.parseLong(id));
        //Profil profil = this.profileRepository.findProfileByUser(user_management);
//        if (profil == null) {
//            throw new RuntimeException("User not found");
//        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("/me/security")
    public ResponseEntity<UserWithoutPasswordAndProfilDTO> getUser(@AuthenticationPrincipal String id) {
        //UserWithoutPasswordAndProfilDTO user_management = this.userRepository.userById(Long.parseLong(id));
//        if (user_management == null) {
//            throw new RuntimeException("User not found");
//        }
        return new ResponseEntity<>(null, HttpStatus.OK);
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
