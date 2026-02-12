package com.epitech.pictmanager.modules.media_management.infrastructure.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@Setter
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "caption", nullable = false)
    private String caption;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    public Post(Long id, Long userId, String caption, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.caption = caption;
        this.createdAt = createdAt;
    }

    public Post() {
    }


}
