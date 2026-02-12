package com.epitech.pictmanager.modules.media_management.application.services.port;

import com.epitech.pictmanager.modules.media_management.application.services.MediaDimension;
import org.springframework.web.multipart.MultipartFile;

public interface MediaServicePort {
    String upload(MultipartFile files, String path);

    MediaDimension extractDimensions(MultipartFile file);
}
