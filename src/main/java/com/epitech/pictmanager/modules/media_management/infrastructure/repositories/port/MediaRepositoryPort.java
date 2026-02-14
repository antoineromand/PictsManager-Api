package com.epitech.pictmanager.modules.media_management.infrastructure.repositories.port;

import com.epitech.pictmanager.modules.media_management.domain.Media;

public interface MediaRepositoryPort {
    void save(Media media);
}
