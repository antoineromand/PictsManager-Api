package com.epitech.pictmanager.components.auth.dto;

import com.epitech.pictmanager.models.Profil;
import com.epitech.pictmanager.models.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import javax.validation.Validation.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
@Builder
@Data
public class RegisterDto {
    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    private String dateOfBirth;
    private boolean isBanned = false;
    private boolean isPublic = true;
    private String description;
    private String profilePicture;
    private String coverPicture;

    public static User toUser(RegisterDto registerDto) {
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        user.setEmail(registerDto.getEmail());
        user.setDateOfBirth(new Date(registerDto.getDateOfBirth()));
        user.setBanned(false);
        user.setPublic(true);
        return user;
    }

    public static Profil toProfil(RegisterDto registerDto, User user) {
        Profil profil = new Profil();
        profil.setDescription(registerDto.getDescription());
        profil.setProfilePicture(registerDto.getProfilePicture());
        profil.setCoverPicture(registerDto.getCoverPicture());
        profil.setUser(user);
        return profil;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getDescription() {
        return description;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

}
