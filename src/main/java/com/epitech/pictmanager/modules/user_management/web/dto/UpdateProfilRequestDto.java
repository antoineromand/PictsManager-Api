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

    public UpdateProfilRequestDto() {
    }

    public UpdateProfilRequestDto(String description, String profilePicture, String coverPicture) {
    }

    public UserProfileDomain toDomain(String publicId) {
        return new UserProfileDomain(publicId, description, coverPicture, profilePicture);
    }

}
