package com.epitech.pictmanager.modules.media_management.infrastructure.model.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class LikeId implements Serializable {
    @Column(name = "post_id")
    private Long postId;
    @Column(name = "user_id")
    private Long userId;
}
