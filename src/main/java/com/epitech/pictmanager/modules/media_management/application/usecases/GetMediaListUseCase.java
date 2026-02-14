package com.epitech.pictmanager.modules.media_management.application.usecases;

import com.epitech.pictmanager.modules.media_management.application.read.MediaListReadModel;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.MediaReadRepositoryPort;
import com.epitech.pictmanager.shared.contracts.repositories.UserLookUpRepositoryPort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class GetMediaListUseCase {
    private final MediaReadRepositoryPort mediaReadRepository;
    private final UserLookUpRepositoryPort userLookUpRepository;

    public GetMediaListUseCase(MediaReadRepositoryPort mediaReadRepository, UserLookUpRepositoryPort userLookUpRepository) {
        this.mediaReadRepository = mediaReadRepository;
        this.userLookUpRepository = userLookUpRepository;
    }

    public MediaListReadModel getUserMediaList(String publicId, int pageNumber, int pageSize) {
        Long userId = this.userLookUpRepository.getUserIdWithPublicId(publicId);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));

        return mediaReadRepository.getUserMediaList(userId, pageable);
    }
}
