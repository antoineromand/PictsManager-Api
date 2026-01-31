package com.epitech.pictmanager.components.image_manager.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

public class ImageUploadDto {
    @Getter @Setter
    private String name;

    @Getter @Setter
    private MultipartFile image;

    @Getter @Setter
    private String description;

    public ImageUploadDto(String name, @NonNull MultipartFile image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }


}