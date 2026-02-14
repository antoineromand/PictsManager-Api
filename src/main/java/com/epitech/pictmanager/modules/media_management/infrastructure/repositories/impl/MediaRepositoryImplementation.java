package com.epitech.pictmanager.modules.media_management.infrastructure.repositories.impl;

import com.epitech.pictmanager.modules.media_management.domain.Media;
import com.epitech.pictmanager.modules.media_management.infrastructure.models.MediaEntity;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.jpa.MediaJpaRepository;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.port.MediaRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Objects;

@Service
public class MediaRepositoryImplementation implements MediaRepositoryPort {
    private final MediaJpaRepository mediaJpaRepository;

    public MediaRepositoryImplementation(final MediaJpaRepository mediaJpaRepository) {
        this.mediaJpaRepository = mediaJpaRepository;
    }

    @Override
    public void save(Media media) {
        this.mediaJpaRepository.save(this.toEntity(media));
    }

    public MediaEntity toEntity(Media media) {
        return new MediaEntity(
                media.id(),
                media.userId(),
                media.originalKey(),
                media.mimeType(),
                Objects.isNull(media.width()) ? null : media.width().longValue(),
                Objects.isNull(media.height()) ? null : media.height().longValue(),
                Objects.isNull(media.createdAt()) ? null : LocalDateTime.ofInstant(media.createdAt(), ZoneOffset.UTC),
                media.status().name()
        );
    }
}
