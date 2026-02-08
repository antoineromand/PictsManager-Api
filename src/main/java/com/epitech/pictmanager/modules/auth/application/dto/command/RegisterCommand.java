package com.epitech.pictmanager.modules.auth.application.dto.command;

import java.time.LocalDate;

public record RegisterCommand(
        String username,
        String password,
        String email,
        LocalDate birthDate,
        boolean isBanned,
        boolean isPublic,
        String description,
        String profilePicture,
        String coverPicture
) {

}
