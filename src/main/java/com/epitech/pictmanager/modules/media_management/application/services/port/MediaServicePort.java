package com.epitech.pictmanager.modules.media_management.application.services.port;

import com.epitech.pictmanager.modules.media_management.application.services.PreparedMedia;
import org.springframework.web.multipart.MultipartFile;

public interface MediaServicePort {
    PreparedMedia prepare(MultipartFile file);

    void addToStorage(byte[] bytes, String contentType, String key);
}
