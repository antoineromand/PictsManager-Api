package com.epitech.pictmanager.modules.media_management.domain;

import java.time.Instant;
import java.util.Objects;


public class Media {

    private final String id;
    private final Long userId;

    private final String originalKey;

    private final String mimeType;
    private MediaStatus status;

    private Integer width;
    private Integer height;

    private final Instant createdAt;

    private Media(String id,
                  Long userId,
                  String originalKey,
                  String mimeType,
                  MediaStatus status,
                  Integer width,
                  Integer height,
                  Instant createdAt) {

        this.id = Objects.requireNonNull(id);
        this.userId = Objects.requireNonNull(userId);
        this.originalKey = Objects.requireNonNull(originalKey);
        this.mimeType = Objects.requireNonNull(mimeType);
        this.status = Objects.requireNonNull(status);
        this.width = width;
        this.height = height;
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public static Media initUpload(String id, Long userId, String originalKey, String mimeType, Instant now) {
        if (originalKey.isBlank()) throw new IllegalArgumentException("originalKey required");
        if (mimeType.isBlank()) throw new IllegalArgumentException("mimeType required");
        return new Media(id, userId, originalKey, mimeType, MediaStatus.UPLOADING, null, null, now);
    }

    public void markReady(int width, int height) {
        if (status != MediaStatus.UPLOADING) {
            throw new IllegalStateException("Can only mark READY from UPLOADING");
        }
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Invalid dimensions");
        }
        this.width = width;
        this.height = height;
        this.status = MediaStatus.READY;
    }

    public void markFailed() {
        if (status == MediaStatus.READY) {
            throw new IllegalStateException("Cannot mark FAILED once READY");
        }
        this.status = MediaStatus.FAILED;
    }

    public boolean isReady() {
        return status == MediaStatus.READY;
    }

    public String id() {
        return id;
    }

    public Long userId() {
        return userId;
    }

    public String originalKey() {
        return originalKey;
    }

    public String mimeType() {
        return mimeType;
    }

    public MediaStatus status() {
        return status;
    }

    public Integer width() {
        return width;
    }

    public Integer height() {
        return height;
    }

    public Instant createdAt() {
        return createdAt;
    }
}
