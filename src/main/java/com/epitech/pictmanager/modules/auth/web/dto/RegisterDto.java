package com.epitech.pictmanager.modules.auth.web.dto;

import com.epitech.pictmanager.modules.auth.application.dto.command.RegisterCommand;
import com.epitech.pictmanager.modules.auth.domain.UserDomain;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@RequiredArgsConstructor
public class RegisterDto {
    @NotBlank
    @Size(min = 8, max = 50)
    @Getter @Setter
    private String username;

    @NotBlank
    @Size(min = 8, max = 100)
    @Getter @Setter
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    @Getter @Setter
    private String email;

    @Getter @Setter
    private LocalDate birthDate;

    @Getter @Setter
    private boolean isBanned = false;

    @Getter @Setter
    private boolean isPublic = true;

    @Getter @Setter
    private String description;

    @Getter @Setter
    private String profilePicture;

    @Getter @Setter
    private String coverPicture;

    public static UserDomain toUserDomain(RegisterDto registerDto, String publicId) {
        return new UserDomain(
                publicId,
                registerDto.getUsername(),
                registerDto.getPassword(),
                registerDto.getEmail(),
                registerDto.getBirthDate(),
                false,
                true
        );
    }

    public RegisterCommand toCommand() {
        return new RegisterCommand(
                this.username,
                this.password,
                this.email,
                this.birthDate,
                false,
                true,
                this.description,
                this.profilePicture,
                this.coverPicture
        );
    }

//    public static Profil toProfil(RegisterDto registerDto, Long userId) {
//        Profil profil = new Profil();
//        profil.setDescription(null);
//        profil.setProfilePicture(null);
//        profil.setCoverPicture(null);
//        profil.setUserId(userId);
//        return profil;
//    } remplacer par UserProfilDomain

}
