package com.epitech.pictmanager.modules.user_management.web.dto.response;

import java.util.List;

public record SearchListResponseDTO(int total, List<UserProfileSearchView> userProfiles) {
}
