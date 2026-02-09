package com.epitech.pictmanager.modules.auth.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

public class UserDomain {
    @Getter
    @Setter
    private String userId;
    @Getter
    @Setter
    private String username;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private boolean isBanned;
    @Getter
    @Setter
    private boolean isPublic;
    @Getter
    @Setter
    private LocalDate birthDate;

    public UserDomain(String id, String username, String password, String email, LocalDate birthDate, Boolean isBanned, Boolean isPublic) {
        this.userId = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.isBanned = isBanned;
        this.isPublic = isPublic;
    }
}
