package com.epitech.pictmanager.modules.user_management.web.dto.response;

import com.epitech.pictmanager.modules.user_management.web.dto.view.UserProfileSearchView;

import java.util.List;

public record SearchListResponseDTO(int total, List<UserProfileSearchView> userProfiles) {
}
