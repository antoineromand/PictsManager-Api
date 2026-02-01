package com.epitech.pictmanager.modules.user_management.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
