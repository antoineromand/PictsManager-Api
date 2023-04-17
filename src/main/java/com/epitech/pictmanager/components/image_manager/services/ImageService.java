package com.epitech.pictmanager.components.image_manager.services;

import com.epitech.pictmanager.components.image_manager.dto.ImageUploadDto;
import com.epitech.pictmanager.components.image_manager.repositories.ImageJpaRepository;
import com.epitech.pictmanager.models.Image;
import com.epitech.pictmanager.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service()
public class ImageService {
    @Autowired
    private ImageJpaRepository imageJpaRepository;
    public Boolean save(ImageUploadDto imageUploadDto, String path, User user) {
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
            image.setDate(new Date());
            image.setUser(user);
            imageJpaRepository.save(image);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Error while saving image");
        }
    }
}
