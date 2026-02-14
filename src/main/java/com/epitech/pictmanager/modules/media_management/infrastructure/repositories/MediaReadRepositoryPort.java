package com.epitech.pictmanager.modules.media_management.infrastructure.repositories;

import com.epitech.pictmanager.modules.media_management.application.read.MediaListReadModel;
import org.springframework.data.domain.Pageable;

public interface MediaReadRepositoryPort {
    MediaListReadModel getUserMediaList(Long userId, Pageable pageable);
}
