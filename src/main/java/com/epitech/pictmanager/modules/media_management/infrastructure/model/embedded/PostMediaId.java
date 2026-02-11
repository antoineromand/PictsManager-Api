package com.epitech.pictmanager.modules.media_management.infrastructure.model.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PostMediaId {
    @Column(name = "post_id")
    private String postId;
    @Column(name = "media_id")
    private String mediaId;

}
