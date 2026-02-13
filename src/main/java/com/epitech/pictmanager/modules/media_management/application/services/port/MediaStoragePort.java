package com.epitech.pictmanager.modules.media_management.application.services.port;

import org.springframework.web.multipart.MultipartFile;

public interface MediaStoragePort {
    void upload(MultipartFile file, String key);
}
