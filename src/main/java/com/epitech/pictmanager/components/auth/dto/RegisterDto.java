package com.epitech.pictmanager.components.auth.dto;

import java.util.Date;
import javax.validation.Validation.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
    private boolean isBanned;
    private boolean isPublic;
    private String description;
    private String profilePicture;
    private String coverPicture;

    public RegisterDto(String username, String password, String email, String dateOfBirth, boolean isBanned, boolean isPublic) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.isBanned = isBanned;
        this.isPublic = isPublic;
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
