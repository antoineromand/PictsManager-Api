package com.epitech.pictmanager.modules.user_management.web.dto;

import com.epitech.pictmanager.modules.user_management.domain.UserProfileDomain;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfilRequestDto {

    private String description;
    private String profilePicture;
    private String coverPicture;

    public UpdateProfilRequestDto(String coverPicture, String description, String picture) {
        this.coverPicture = coverPicture;
        this.description = description;
        this.profilePicture = picture;
    }

    public UserProfileDomain toDomain(String publicId) {
        return new UserProfileDomain(publicId, description, coverPicture, profilePicture);
    }

}
