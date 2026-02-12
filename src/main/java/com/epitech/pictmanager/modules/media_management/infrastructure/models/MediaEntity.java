package com.epitech.pictmanager.modules.media_management.infrastructure.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "media")
@Setter
@Getter

public class MediaEntity {
    @Id
    private String id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "original_key", nullable = false)
    private String originalKey;

    @Column(name = "mime_type", length = 50, nullable = false)
    private String mimeType;

    @Column(name = "width", nullable = false)
    private Long width;

    @Column(name = "height", nullable = false)
    private Long height;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "status", nullable = false)
    private String status;


    public MediaEntity() {
    }

    public MediaEntity(String id, Long userId, String originalKey, String mimeType, Long width, Long height, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.originalKey = originalKey;
        this.mimeType = mimeType;
        this.width = width;
        this.height = height;
        this.createdAt = createdAt;
    }

}
