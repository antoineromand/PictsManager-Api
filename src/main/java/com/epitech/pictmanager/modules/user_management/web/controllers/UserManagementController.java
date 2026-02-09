package com.epitech.pictmanager.modules.user_management.web.controllers;


import com.epitech.pictmanager.modules.user_management.application.usecases.profil.GetUserProfileUseCase;
import com.epitech.pictmanager.modules.user_management.application.usecases.profil.UpdateUserProfileUseCase;
import com.epitech.pictmanager.modules.user_management.web.dto.UpdateProfilRequestDto;
import com.epitech.pictmanager.modules.user_management.web.dto.response.UpdateProfilResponseDTO;
import com.epitech.pictmanager.modules.user_management.web.dto.view.UserProfileView;
import com.epitech.pictmanager.shared.responses.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("private/api/user")
public class UserManagementController {
    private final GetUserProfileUseCase getUserProfileUseCase;
    private final UpdateUserProfileUseCase updateUserProfileUseCase;

    public UserManagementController(GetUserProfileUseCase getUserProfileUseCase, UpdateUserProfileUseCase updateUserProfileUseCase) {
        this.getUserProfileUseCase = getUserProfileUseCase;
        this.updateUserProfileUseCase = updateUserProfileUseCase;
    }

    @GetMapping("/me/profile")
    public GenericResponse<UserProfileView> getProfil(@AuthenticationPrincipal String publicId) {
        UserProfileView profil = this.getUserProfileUseCase.execute(publicId);
        return new GenericResponse<>(null, HttpStatus.OK.value(), profil);
    }


    @PutMapping("/me/profile")
    public GenericResponse<UpdateProfilResponseDTO> updateProfil(@AuthenticationPrincipal String id, @RequestBody() UpdateProfilRequestDto dto) {
        return new GenericResponse<>(
                "Profile updated succesfully.",
                HttpStatus.OK.value(),
                this.updateUserProfileUseCase.execute(dto.toDomain(id))
        );
    }

    // -> user : update isPublic true or false;
    //    @PutMapping("/me/security")
    //    public ResponseEntity<GenericUpdateResponse> updateSecurity(@AuthenticationPrincipal String id, @RequestBody() UpdateSecurityDto updateSecurityDto) {
    //        return this.userService.updateUserSecurity(id, updateSecurityDto);
    //    }

    // -> user : delete user -> on cascade will delete profil
//    @DeleteMapping("/me")
//    public ResponseEntity<GenericResponse<Void>> deleteUser(@AuthenticationPrincipal String id) {
//        return this.userService.deleteUserAndProfil(id);
//    }

}
