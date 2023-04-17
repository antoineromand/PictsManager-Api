package com.epitech.pictmanager.components.image_manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public class ImageUploadDto {
    @JsonProperty("name")
    private String name;

    @JsonProperty("image")
    private MultipartFile image;

    @JsonProperty("description")
    private String description;

    public ImageUploadDto(String name, MultipartFile image, String description) {
        this.name = name;
        this.image = image;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}