package com.epitech.pictmanager.modules.user_management.application.usecases;

import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.ProfileRepositoryPort;
import com.epitech.pictmanager.modules.user_management.web.dto.response.SearchListResponseDTO;
import com.epitech.pictmanager.modules.user_management.web.dto.response.UserProfileSearchView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchUserProfileUseCase {

    public final ProfileRepositoryPort profileRepositoryPort;

    public SearchUserProfileUseCase(ProfileRepositoryPort profileRepositoryPort) {
        this.profileRepositoryPort = profileRepositoryPort;
    }

    public SearchListResponseDTO execute(String searchInput, int limit, int offset) {
        int total = this.profileRepositoryPort.getTotalFromSearch(searchInput);

        List<UserProfileSearchView> searchViews = this.profileRepositoryPort.
                getUsersAndProfilsWithSearchInput(searchInput, limit, offset).stream().map(
                read -> new UserProfileSearchView(
                        read.username(), read.picture(), read.description(), read.isPublic()
                )
        ).toList();

        return new SearchListResponseDTO(total, searchViews);
    }
}
