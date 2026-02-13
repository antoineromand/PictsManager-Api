package com.epitech.pictmanager.modules.media_management.web.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UploadMediaRequestDto(List<MultipartFile> files) {
}
