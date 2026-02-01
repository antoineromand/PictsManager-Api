package com.epitech.pictmanager.components.auth.dto;

import com.epitech.pictmanager.models.Profil;
import com.epitech.pictmanager.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
public class RegisterDto {
    @NotBlank
    @Size(min = 3, max = 50)
    @Getter @Setter
    private String username;

    @NotBlank
    @Size(min = 6, max = 100)
    @Getter @Setter
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    @Getter @Setter
    private String email;

    @Getter @Setter
    private LocalDate dateOfBirth;

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

    public static User toUser(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        user.setEmail(registerDto.getEmail());
        user.setDateOfBirth(registerDto.getDateOfBirth());
        user.setIsBanned(false);
        user.setIsPublic(true);
        return user;
    }

    public static Profil toProfil(RegisterDto registerDto, User user) {
        Profil profil = new Profil();
        profil.setDescription(null);
        profil.setProfilePicture(null);
        profil.setCoverPicture(null);
        profil.setUser(user);
        return profil;
    }

}
