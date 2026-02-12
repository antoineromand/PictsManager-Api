package com.epitech.pictmanager.modules.media_management.application.usecases;

import com.epitech.pictmanager.modules.media_management.application.command.UploadMediaCommand;
import com.epitech.pictmanager.modules.media_management.application.services.MediaDimension;
import com.epitech.pictmanager.modules.media_management.application.services.port.MediaServicePort;
import com.epitech.pictmanager.modules.media_management.domain.Media;
import com.epitech.pictmanager.modules.media_management.domain.MediaStatus;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.port.MediaRepositoryPort;
import com.epitech.pictmanager.modules.media_management.web.dto.UploadedMediasResponseDto;
import com.epitech.pictmanager.shared.contracts.repositories.UserLookUpRepositoryPort;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class UploadMediaUseCase {
    private final MediaServicePort mediaServicePort;
    private final MediaRepositoryPort mediaRepositoryPort;
    private final UserLookUpRepositoryPort userLookUpRepositoryPort;

    public UploadMediaUseCase(MediaServicePort mediaServicePort, MediaRepositoryPort mediaRepositoryPort, UserLookUpRepositoryPort userLookUpRepositoryPort) {
        this.mediaServicePort = mediaServicePort;
        this.mediaRepositoryPort = mediaRepositoryPort;
        this.userLookUpRepositoryPort = userLookUpRepositoryPort;
    }

    public List<UploadedMediasResponseDto> execute(UploadMediaCommand command) {
        Long userId = this.userLookUpRepositoryPort.getUserIdWithPublicId(command.publicId());
        if (Objects.isNull(command.files()) || command.files().isEmpty()) {
            throw new IllegalArgumentException("At least one file must be provided");
        }
        var responses = new ArrayList<UploadedMediasResponseDto>();
        command.files().forEach(file -> {
            String uuid = UUID.randomUUID().toString();
            String filename = Objects.requireNonNull(file.getOriginalFilename());
            int dot = filename.lastIndexOf('.');
            String extension = (dot >= 0) ? filename.substring(dot) : "";

            String key = "uploads/" + uuid + "/original" + extension;
            Media media = Media.initUpload(
                    uuid,
                    userId,
                    key,
                    Objects.requireNonNull(file.getContentType()),
                    Instant.now()
            );
            this.mediaRepositoryPort.save(media);
            try {
                MediaDimension dimension = this.mediaServicePort.extractDimensions(file);
                this.mediaServicePort.upload(file, media.originalKey());
                media.markReady(dimension.width(), dimension.height());
                this.mediaRepositoryPort.save(media);
                responses.add(new UploadedMediasResponseDto(media.id(), MediaStatus.READY.name(), null));
            } catch (Exception e) {
                media.markFailed();
                this.mediaRepositoryPort.save(media);
                responses.add(new UploadedMediasResponseDto(media.id(), MediaStatus.FAILED.name(), "Failed to upload media"));
            }
        });
        return responses;
    }
}
