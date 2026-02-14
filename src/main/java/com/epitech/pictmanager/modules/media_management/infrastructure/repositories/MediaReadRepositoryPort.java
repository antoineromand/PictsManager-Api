package com.epitech.pictmanager.modules.media_management.infrastructure.repositories;

import com.epitech.pictmanager.modules.media_management.application.read.MediaRowReadModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MediaReadRepositoryPort {
    List<MediaRowReadModel> getUserMediaList(Long userId, Pageable pageable);
}
