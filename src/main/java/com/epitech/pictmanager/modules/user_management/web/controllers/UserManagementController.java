package com.epitech.pictmanager.modules.user_management.web.controllers;

import com.epitech.pictmanager.modules.auth.infrastructure.repositories.ports.UserRepositoryPort;
import com.epitech.pictmanager.modules.user_management.application.dto.UpdateProfilDto;
import com.epitech.pictmanager.modules.user_management.application.dto.UpdateSecurityDto;
import com.epitech.pictmanager.modules.user_management.application.dto.UserWithoutPasswordAndProfilDTO;
import com.epitech.pictmanager.modules.user_management.application.usecases.profil.GetUserProfileUseCase;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.jpa.ProfileJpaRepository;
import com.epitech.pictmanager.modules.user_management.application.services.UserProfilService;
import com.epitech.pictmanager.modules.user_management.web.dto.view.UserProfileView;
import com.epitech.pictmanager.shared.responses.GenericResponse;
import com.epitech.pictmanager.shared.responses.GenericUpdateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("private/api/user")
public class UserManagementController {
    private final UserRepositoryPort userRepository;
    private final ProfileJpaRepository profileRepository;
    private final GetUserProfileUseCase getUserProfileUseCase;
    private final UserProfilService userService;

    public UserManagementController(UserRepositoryPort userRepository, ProfileJpaRepository profileRepository, GetUserProfileUseCase getUserProfileUseCase, UserProfilService userService) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
        this.getUserProfileUseCase = getUserProfileUseCase;
        this.userService = userService;
    }

    @GetMapping("/me/profil")
    public GenericResponse<UserProfileView> getProfil(@AuthenticationPrincipal String publicId) {

        UserProfileView profil = this.getUserProfileUseCase.execute(publicId);

        return new GenericResponse<>(null, HttpStatus.OK.value(), profil);
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
    public ResponseEntity<GenericResponse<Void>> deleteUser(@AuthenticationPrincipal String id) {
        return this.userService.deleteUserAndProfil(id);
    }

}
