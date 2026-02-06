package com.epitech.pictmanager.modules.user_management.web.dto.response;

public record UserProfileSearchView(String username, String picture, String description, boolean isPublic) {
}
