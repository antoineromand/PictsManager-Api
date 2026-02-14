package com.epitech.pictmanager.modules.media_management.application.usecases;

import com.epitech.pictmanager.modules.media_management.application.command.UploadMediaCommand;
import com.epitech.pictmanager.modules.media_management.application.services.port.MediaServicePort;
import com.epitech.pictmanager.modules.media_management.domain.Media;
import com.epitech.pictmanager.modules.media_management.domain.MediaStatus;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.port.MediaRepositoryPort;
import com.epitech.pictmanager.modules.media_management.web.dto.UploadMediaResponseDto;
import com.epitech.pictmanager.shared.contracts.repositories.UserLookUpRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UploadMediaUseCase {
    private final MediaServicePort mediaServicePort;
    private final MediaRepositoryPort mediaRepositoryPort;
    private final UserLookUpRepositoryPort userLookUpRepositoryPort;

    public UploadMediaUseCase(MediaServicePort mediaServicePort, MediaRepositoryPort mediaRepositoryPort, UserLookUpRepositoryPort userLookUpRepositoryPort) {
        this.mediaServicePort = mediaServicePort;
        this.mediaRepositoryPort = mediaRepositoryPort;
        this.userLookUpRepositoryPort = userLookUpRepositoryPort;
    }

    public List<UploadMediaResponseDto> execute(UploadMediaCommand command) {
        if (Objects.isNull(command.files()) || command.files().isEmpty()) {
            throw new IllegalArgumentException("At least one file must be provided");
        }
        Long userId = this.userLookUpRepositoryPort.getUserIdWithPublicId(command.publicId());

        int parallelism = 3;
        ExecutorService pool = Executors.newFixedThreadPool(parallelism);

        try {
            List<CompletableFuture<UploadMediaResponseDto>> futures =
                    command.files().
                            stream().map(file -> CompletableFuture
                                    .supplyAsync(() -> this.uploadMedia(file, userId), pool))
                            .toList();

            return futures.stream().map(CompletableFuture::join).toList();
        } finally {
            pool.shutdown();
        }

    }

    private UploadMediaResponseDto uploadMedia(MultipartFile file, Long userId) {
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
            var prepared = mediaServicePort.prepare(file);
            this.mediaServicePort.addToStorage(prepared.bytes(), prepared.contentType(), media.originalKey());
            media.markReady(prepared.dimension().width(), prepared.dimension().height());
            this.mediaRepositoryPort.save(media);
            return new UploadMediaResponseDto(media.id(), MediaStatus.READY.name(), null);
        } catch (Exception e) {
            media.markFailed();
            this.mediaRepositoryPort.save(media);
            return new UploadMediaResponseDto(media.id(), MediaStatus.FAILED.name(), "Failed to upload media");
        }
    }
}
