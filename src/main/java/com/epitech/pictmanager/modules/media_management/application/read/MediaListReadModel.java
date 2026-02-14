package com.epitech.pictmanager.modules.media_management.application.read;

import java.util.List;

public record MediaListReadModel(Long totalElements, List<MediaRowReadModel> medias) {
}
