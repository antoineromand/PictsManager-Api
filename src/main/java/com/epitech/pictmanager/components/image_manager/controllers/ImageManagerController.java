package com.epitech.pictmanager.components.image_manager.controllers;

import com.epitech.pictmanager.components.image_manager.services.UploadService;
import com.epitech.pictmanager.components.user.repositories.UserJpaRepository;
import com.epitech.pictmanager.responses.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;


@RestController()
@RequestMapping("private/api/image-manager")
public class ImageManagerController {
    @Autowired
    private UploadService uploadService;
    @Autowired
    private UserJpaRepository userJpaRepository;

    @PostMapping("upload")
    public Object test(@AuthenticationPrincipal() String id,
                       @RequestParam("image") MultipartFile image,
                       @RequestParam("description") String description) throws IOException {
        return uploadService.upload(id, image, description);
    }


    @GetMapping("/{userId}/{imageName}")
    public URL serveImage(@AuthenticationPrincipal() String id,
                          @PathVariable("userId") String userId,
                          @PathVariable("imageName") String imageName,
                          HttpServletRequest request) throws IOException {
        if(userJpaRepository.findUserById(Long.parseLong(id)) != null) {
            return this.uploadService.get(userId, imageName);
        } else {
            throw new RuntimeException("User not found");
        }

    }
}
