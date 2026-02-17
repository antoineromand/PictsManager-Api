package com.epitech.pictmanager.modules.media_management.infrastructure.repositories;

import com.epitech.pictmanager.modules.media_management.domain.Post;

public interface PostRepositoryPort {
    Post save(Post post);
}
