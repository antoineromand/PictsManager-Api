package com.epitech.pictmanager.modules.image_manager.services;

import com.epitech.pictmanager.modules.image_manager.dto.ImageUploadDto;
import com.epitech.pictmanager.modules.image_manager.repositories.ImageJpaRepository;
import com.epitech.pictmanager.models.Image;
import org.springframework.stereotype.Service;

@Service()
public class ImageService {

    private final ImageJpaRepository imageJpaRepository;
    public ImageService(ImageJpaRepository imageJpaRepository) {
        this.imageJpaRepository = imageJpaRepository;
    }
    public Boolean save(ImageUploadDto imageUploadDto, String path, Long userId) {
        try {
            if(imageJpaRepository.findImageByPath(path) != null) {
                throw new RuntimeException("Image already exists");
            }
            if(imageUploadDto.getName() == null || imageUploadDto.getName().isEmpty()) {
                throw new RuntimeException("Image name is empty");
            }
            Image image = new Image();
            image.setPath(path);
            image.setName(imageUploadDto.getName());
            image.setDescription(imageUploadDto.getDescription());
            image.setUserId(userId);
            imageJpaRepository.save(image);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error while saving image");
        }
    }
}
