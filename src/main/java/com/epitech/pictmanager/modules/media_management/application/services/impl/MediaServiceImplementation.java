package com.epitech.pictmanager.modules.media_management.application.services.impl;

import com.epitech.pictmanager.modules.media_management.application.services.MediaDimension;
import com.epitech.pictmanager.modules.media_management.application.services.PreparedMedia;
import com.epitech.pictmanager.modules.media_management.application.services.port.MediaServicePort;
import com.epitech.pictmanager.modules.media_management.application.services.port.MediaStoragePort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
public class MediaServiceImplementation implements MediaServicePort {

    private final MediaStoragePort mediaStoragePort;

    public MediaServiceImplementation(MediaStoragePort mediaStoragePort) {
        this.mediaStoragePort = mediaStoragePort;
    }

    @Override
    public PreparedMedia prepare(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String ct = file.getContentType();
            BufferedImage img = ImageIO.read(new java.io.ByteArrayInputStream(bytes));
            if (img == null) throw new IllegalArgumentException("Image invalide");
            return new PreparedMedia(bytes, ct, new MediaDimension(img.getWidth(), img.getHeight()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addToStorage(byte[] bytes, String contentType, String key) {
        mediaStoragePort.upload(bytes, contentType, key);
    }

}
