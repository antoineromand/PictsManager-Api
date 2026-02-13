package com.epitech.pictmanager.modules.media_management.application.services.impl;

import com.epitech.pictmanager.modules.media_management.application.services.MediaDimension;
import com.epitech.pictmanager.modules.media_management.application.services.port.MediaServicePort;
import com.epitech.pictmanager.modules.media_management.application.services.port.MediaStoragePort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

@Service
public class MediaServiceImplementation implements MediaServicePort {

    private final MediaStoragePort mediaStoragePort;

    public MediaServiceImplementation(MediaStoragePort mediaStoragePort) {
        this.mediaStoragePort = mediaStoragePort;
    }

    @Override
    public MediaDimension extractDimensions(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            BufferedImage bufferedImage = ImageIO.read(is);
            if (bufferedImage == null) {
                throw new IllegalArgumentException("Le fichier n'est pas une image valide");
            }
            return new MediaDimension(bufferedImage.getWidth(), bufferedImage.getHeight());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addToStorage(MultipartFile file, String key) {
        this.mediaStoragePort.upload(file, key);
    }

}
