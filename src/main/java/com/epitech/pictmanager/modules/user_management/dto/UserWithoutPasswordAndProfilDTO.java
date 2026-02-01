package com.epitech.pictmanager.modules.user_management.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@Setter
public class UserWithoutPasswordAndProfilDTO {
    private String username;
    private String email;
    private LocalDate dateOfBirth;
    private Boolean isPublic;

    public UserWithoutPasswordAndProfilDTO(String username, String email, LocalDate dateOfBirth, Boolean isPublic) {
        this.username = username;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isPublic = isPublic;
    }
}