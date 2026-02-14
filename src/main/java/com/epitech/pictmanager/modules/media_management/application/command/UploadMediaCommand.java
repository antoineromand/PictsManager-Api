package com.epitech.pictmanager.modules.media_management.application.command;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UploadMediaCommand(List<MultipartFile> files, String publicId) {
}
