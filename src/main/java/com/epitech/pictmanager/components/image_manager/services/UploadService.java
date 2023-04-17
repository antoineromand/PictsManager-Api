package com.epitech.pictmanager.components.image_manager.services;

import com.epitech.pictmanager.components.image_manager.dto.ImageUploadDto;
import com.epitech.pictmanager.components.user.repositories.UserJpaRepository;
import com.epitech.pictmanager.models.User;
import com.epitech.pictmanager.responses.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service()
public class UploadService {
    @Autowired
    private UserJpaRepository userJpaRepository;
    @Autowired
    private ImageService imageService;

    public ResponseEntity<GenericResponse> uploadImage(String id, MultipartFile image, String description) {
        try {
            User user = userJpaRepository.findUserById(Long.parseLong(id));
            if (user == null) {
                throw new RuntimeException("User not found");
            }
            byte[] bytes = image.getBytes();
            File userFolder = new File(System.getProperty("user.home") + "/images-manager/" + user.getId());

            if(!userFolder.exists()) {
                userFolder.mkdir();
            }

            // Write the image to the folder
            Path path = Paths.get(userFolder.getPath(), image.getOriginalFilename());
            Files.write(path, bytes);
            ImageUploadDto imageUploadDto = new ImageUploadDto(image.getOriginalFilename(), image, description);

            if (imageService.save(imageUploadDto, path.toString(), user)) {
                return ResponseEntity.status(HttpStatus.CREATED).body(new GenericResponse("Image uploaded successfully", HttpStatus.CREATED.value()));
            } else {
                throw new RuntimeException("Error while saving image");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error while uploading image");
        }
    }
}