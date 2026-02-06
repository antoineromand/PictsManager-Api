package com.epitech.pictmanager.modules.user_management.web.dto.response;

public record UserProfilDetailView(
        String username,
        String description,
        String picture,
        String coverPicture,
        boolean isPublic
) {
}
