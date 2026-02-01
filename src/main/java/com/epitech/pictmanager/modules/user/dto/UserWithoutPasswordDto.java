package com.epitech.pictmanager.modules.user.dto;

import com.epitech.pictmanager.models.Image;
import com.epitech.pictmanager.models.Profil;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

public class UserWithoutPasswordDto {
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private LocalDate dateOfBirth;
    @Getter
    @Setter
    private Boolean isPublic;

    public UserWithoutPasswordDto(String username, String email, LocalDate dateOfBirth, Boolean isPublic) {
        this.username = username;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isPublic = isPublic;
    }
}
