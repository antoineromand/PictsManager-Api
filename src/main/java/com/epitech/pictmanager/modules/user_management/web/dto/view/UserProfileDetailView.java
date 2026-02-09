package com.epitech.pictmanager.modules.user_management.web.dto.view;

public record UserProfileDetailView(
        String username,
        String description,
        String picture,
        String coverPicture,
        boolean isPublic
) {
}
