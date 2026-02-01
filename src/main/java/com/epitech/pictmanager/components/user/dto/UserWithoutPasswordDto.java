package com.epitech.pictmanager.components.user.dto;

import com.epitech.pictmanager.models.Image;
import com.epitech.pictmanager.models.Profil;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
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
    @Getter
    @Setter
    private Profil profil;
    @Getter
    @Setter
    private Set<Image> images;

    public UserWithoutPasswordDto(String username, String email, LocalDate dateOfBirth, Boolean isPublic, Profil profil, Set<Image> images) {
        this.username = username;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isPublic = isPublic;
        this.profil = profil;
        this.images = images;
    }
}
