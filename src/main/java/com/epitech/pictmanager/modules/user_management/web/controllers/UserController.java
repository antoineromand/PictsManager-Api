package com.epitech.pictmanager.modules.user_management.web.controllers;

import com.epitech.pictmanager.modules.auth.domain.UserDomain;
import com.epitech.pictmanager.modules.auth.infrastructure.repositories.ports.UserRepositoryPort;
import com.epitech.pictmanager.modules.user_management.application.dto.UpdateProfilDto;
import com.epitech.pictmanager.modules.user_management.application.dto.UpdateSecurityDto;
import com.epitech.pictmanager.modules.user_management.application.dto.UserWithoutPasswordAndProfilDTO;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.jpa.ProfilJpaRepository;
import com.epitech.pictmanager.modules.user_management.application.services.UserService;
import com.epitech.pictmanager.modules.user_management.infrastructure.models.UserProfil;
import com.epitech.pictmanager.shared.responses.GenericResponse;
import com.epitech.pictmanager.shared.responses.GenericUpdateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("private/api/user")
public class UserController {
    private final UserRepositoryPort userRepository;
    private final ProfilJpaRepository profileRepository;
    private final UserService userService;

    public UserController(UserRepositoryPort userRepository, ProfilJpaRepository profileRepository, UserService userService) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.userService = userService;
    }

    @GetMapping("/me/profil")
    public ResponseEntity<UserProfil> getProfil(@AuthenticationPrincipal UUID publicId) {
        UserDomain user = this.userRepository.getUserByPublicId(publicId);

        //Profil profil = this.profileRepository.findProfileByUser(user_management);
//        if (profil == null) {
//            throw new RuntimeException("User not found");
//       }

        return new ResponseEntity<>(null, HttpStatus.OK);

    }

    @GetMapping("/me/security")
    public ResponseEntity<UserWithoutPasswordAndProfilDTO> getUser(@AuthenticationPrincipal UUID id) {
        //UserWithoutPasswordAndProfilDTO user_management = this.userRepository.userById(Long.parseLong(id));
//        if (user_management == null) {
//            throw new RuntimeException("User not found");
//        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping("/me/profil")
    public ResponseEntity<GenericUpdateResponse> updateProfil(@AuthenticationPrincipal UUID id, @RequestBody() UpdateProfilDto updateProfilDto) {
        return this.userService.updateUserProfil(id, updateProfilDto);
    }

    @PutMapping("/me/security")
    public ResponseEntity<GenericUpdateResponse> updateSecurity(@AuthenticationPrincipal UUID id, @RequestBody() UpdateSecurityDto updateSecurityDto) {
        return this.userService.updateUserSecurity(id, updateSecurityDto);
    }

    @DeleteMapping("/me")
    public ResponseEntity<GenericResponse<Void>> deleteUser(@AuthenticationPrincipal UUID id) {
        return this.userService.deleteUserAndProfil(id);
    }

}
