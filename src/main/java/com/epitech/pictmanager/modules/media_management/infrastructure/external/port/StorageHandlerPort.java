package com.epitech.pictmanager.modules.media_management.infrastructure.external.port;

import com.epitech.pictmanager.modules.media_management.domain.Media;
import org.springframework.web.multipart.MultipartFile;

public interface StorageHandlerPort {
    String insert(Media media, MultipartFile file);

    String remove(String mediaId);
}
