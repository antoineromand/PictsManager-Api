package com.epitech.pictmanager.modules.user_management.application.dto.read;

public record UserPublicProfileReadModel(String username, String description, boolean isPublic, String picture, String coverPicture) {
}
