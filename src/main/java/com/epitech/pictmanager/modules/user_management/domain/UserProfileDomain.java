package com.epitech.pictmanager.modules.user_management.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class UserProfileDomain {
    private final String publicId;
    private String description;
    private String coverPicture;
    private String picture;

    public UserProfileDomain(String publicId, String description, String coverPicture, String picture) {
        this.publicId = publicId;
        this.description = description;
        this.coverPicture = coverPicture;
        this.picture = picture;
    }
}
