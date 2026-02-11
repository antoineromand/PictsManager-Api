package com.epitech.pictmanager.modules.media_management.infrastructure.model;

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
public class Media {
    @Id
    private String id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "original_key", nullable = false)
    private Long originalKey;

    @Column(name = "mime_type", length = 50, nullable = false)
    private String mimeType;

    @Column(name = "width", nullable = false)
    private String width;

    @Column(name = "height", nullable = false)
    private String height;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    public Media(String id, Long userId, Long originalKey, String mimeType, String width, String height, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.originalKey = originalKey;
        this.mimeType = mimeType;
        this.width = width;
        this.height = height;
        this.createdAt = createdAt;
    }

    public Media() {
    }

}
