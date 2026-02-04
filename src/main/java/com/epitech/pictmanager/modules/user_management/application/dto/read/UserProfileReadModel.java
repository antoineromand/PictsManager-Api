package com.epitech.pictmanager.modules.user_management.application.dto.read;

import java.util.Date;

public record UserProfileReadModel(
        String username,
        String email,
        Date dateOfBirth,
        String description,
        String picture,
        String coverPicture,
        boolean isPublic
) {}