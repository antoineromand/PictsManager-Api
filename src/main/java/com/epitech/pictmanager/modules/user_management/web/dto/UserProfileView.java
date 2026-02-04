package com.epitech.pictmanager.modules.user_management.web.dto;

import java.time.LocalDate;

public record UserProfileView(
        String username,
        String email,
        LocalDate dateOfBirth,
        String description,
        String picture,
        String coverPicture,
        boolean isPublic
) {
}
