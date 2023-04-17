package com.epitech.pictmanager.components.image_manager.controllers;

import com.epitech.pictmanager.components.image_manager.dto.ImageUploadDto;
import com.epitech.pictmanager.components.image_manager.repositories.ImageJpaRepository;
import com.epitech.pictmanager.components.image_manager.services.UploadService;
import com.epitech.pictmanager.components.user.repositories.UserJpaRepository;
import com.epitech.pictmanager.responses.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController()
@RequestMapping("private/api/image-manager")
public class ImageManagerController {
    @Autowired
    private UploadService uploadService;

    @PostMapping("upload")
    public ResponseEntity<GenericResponse> uploadImage(@AuthenticationPrincipal() String id,
                                                       @RequestParam("image") MultipartFile image,
                                                       @RequestParam("description") String description
                                                       ) throws IOException {
        return uploadService.uploadImage(id, image, description);
    }
}
