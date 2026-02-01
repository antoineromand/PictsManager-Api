package com.epitech.pictmanager.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity()
@Table(name = "user_profile")
public class Profil {
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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    @Getter @Setter
    private User user;

    public Profil() {
        super();
    }

    public Profil(String description, String profilePicture, String coverPicture) {
        this.description = description;
        this.profilePicture = profilePicture;
        this.coverPicture = coverPicture;
    }
}
