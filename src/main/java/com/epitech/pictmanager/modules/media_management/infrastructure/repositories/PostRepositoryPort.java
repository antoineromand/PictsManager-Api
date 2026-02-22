package com.epitech.pictmanager.modules.media_management.infrastructure.repositories;

import com.epitech.pictmanager.modules.media_management.domain.Post;

public interface PostRepositoryPort {
    void save(Post post);

    boolean exists(Long id);

    void likePost(Long userId, Long postId);

    void unlikePost(Long userId, Long postId);
}
