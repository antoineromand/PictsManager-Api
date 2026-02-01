package com.epitech.pictmanager.modules.auth.infrastructure.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity()
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(unique = true, nullable = false)
    @Getter
    @Setter
    private String username;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false, name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(nullable = false, name = "is_banned")
    @Getter
    @Setter
    private Boolean isBanned;

    @Column(nullable = false, name = "is_public")
    @Getter
    @Setter
    private Boolean isPublic;

    public User() {

    }

    public User(String username, String password, String email, LocalDate dateOfBirth, boolean isBanned, boolean isPublic) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.isBanned = isBanned;
        this.isPublic = isPublic;
    }

}
