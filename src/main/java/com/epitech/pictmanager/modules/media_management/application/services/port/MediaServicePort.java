package com.epitech.pictmanager.modules.media_management.application.services.port;

import com.epitech.pictmanager.modules.media_management.application.services.MediaDimension;
import org.springframework.web.multipart.MultipartFile;

public interface MediaServicePort {
    MediaDimension extractDimensions(MultipartFile file);

    void addToStorage(MultipartFile file, String key);
}
