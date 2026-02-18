package com.epitech.pictmanager.modules.media_management.infrastructure.repositories.impl;

import com.epitech.pictmanager.modules.media_management.application.read.MediaListReadModel;
import com.epitech.pictmanager.modules.media_management.application.read.MediaRowReadModel;
import com.epitech.pictmanager.modules.media_management.infrastructure.models.MediaEntity;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.MediaReadRepositoryPort;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.jpa.MediaJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MediaReadRepositoryImplementation implements MediaReadRepositoryPort {

    private final MediaJpaRepository mediaJpaRepository;

    public MediaReadRepositoryImplementation(MediaJpaRepository mediaJpaRepository) {
        this.mediaJpaRepository = mediaJpaRepository;
    }

    @Override
    public MediaListReadModel getUserMediaList(Long userId, Pageable pageable) {
        Page<MediaEntity> mediaEntities = mediaJpaRepository.findMediaEntitiesByUserId(userId, pageable);
        List<MediaRowReadModel> rows = mediaEntities.stream().map(entity -> new MediaRowReadModel(
                entity.getId(),
                entity.getOriginalKey(),
                entity.getUserId()
        )).toList();

        return new MediaListReadModel(mediaEntities.getTotalElements(), rows);
    }

    @Override
    public List<MediaRowReadModel> getMediasById(List<String> ids) {
        List<MediaEntity> mediaEntities = mediaJpaRepository.findAllById(ids);
        return mediaEntities
                .stream()
                .map(
                        media ->
                                new MediaRowReadModel(
                                        media.getId(),
                                        media.getOriginalKey(),
                                        media.getUserId()
                                )).toList();
    }
}
