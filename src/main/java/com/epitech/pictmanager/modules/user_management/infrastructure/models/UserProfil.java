package com.epitech.pictmanager.modules.user_management.infrastructure.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity()
@Table(name = "user_profile")
public class UserProfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    @Column(nullable = true)
    private String description;

    @Column(nullable = true, name = "profile_picture")
    @Getter @Setter
    private String profilePicture;

    @Column(nullable = true, name = "cover_picture")
    @Getter @Setter
    private String coverPicture;

    @JoinColumn(name = "user_id")
    @Getter @Setter
    private Long userId;

    public UserProfil() {}

    public UserProfil(String description, String profilePicture, String coverPicture, Long userId) {
        this.description = description;
        this.profilePicture = profilePicture;
        this.coverPicture = coverPicture;
        this.userId = userId;
    }
}
