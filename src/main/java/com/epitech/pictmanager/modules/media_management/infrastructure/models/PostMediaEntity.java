package com.epitech.pictmanager.modules.media_management.infrastructure.models;

import com.epitech.pictmanager.modules.media_management.infrastructure.models.embedded.PostMediaId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post_media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostMediaEntity {
    @EmbeddedId
    private PostMediaId postMediaId;

    @Column(name = "position")
    private Long position;
}
