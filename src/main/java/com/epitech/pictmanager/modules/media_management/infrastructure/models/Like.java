package com.epitech.pictmanager.modules.media_management.infrastructure.models;

import com.epitech.pictmanager.modules.media_management.infrastructure.models.embedded.LikeId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "likes")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Like {
    @EmbeddedId
    private LikeId id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

}
