package com.epitech.pictmanager.modules.user_management.application.usecases;

import com.epitech.pictmanager.modules.user_management.application.dto.read.UserProfileReadModel;
import com.epitech.pictmanager.modules.user_management.application.exceptions.ProfileNotFoundException;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.implementation.ProfileRepositoryImpl;
import com.epitech.pictmanager.modules.user_management.web.dto.response.UserProfileView;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.sql.Date;

@Service
public class GetUserProfileUseCase {
    private final ProfileRepositoryImpl profileRepository;
    public GetUserProfileUseCase(ProfileRepositoryImpl profileRepository, ProfileRepositoryImpl profileRepository1) {
        this.profileRepository = profileRepository1;
    }
    public UserProfileView execute(String userId) {
        UserProfileReadModel read = this.profileRepository.getUserAndProfilWithPublicId(userId).orElseThrow(
                ProfileNotFoundException::new
        );
        return new UserProfileView(
                read.username(),
                read.email(),
                convertDateToLocalDate(read.dateOfBirth()),
                read.description(),
                read.picture(),
                read.coverPicture(),
                read.isPublic()
        );
    }

    private LocalDate convertDateToLocalDate(Date date) {
        return date.toLocalDate();
    }
}
