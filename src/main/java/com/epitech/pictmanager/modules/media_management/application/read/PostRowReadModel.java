package com.epitech.pictmanager.modules.media_management.application.read;

import java.time.LocalDateTime;
import java.util.List;

public record PostRowReadModel(Long id, String caption, List<MediaRowReadModel> mediaRowReadModels, int likes,
                               LocalDateTime created_at) {
}
