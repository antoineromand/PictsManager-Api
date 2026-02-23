package com.epitech.pictmanager.modules.media_management.infrastructure.repositories;

import com.epitech.pictmanager.modules.media_management.application.read.PostRowReadModel;

import java.util.List;

public interface PostReadRepositoryPort {
    List<PostRowReadModel> getPostsByUserId(Long userId);

    List<PostRowReadModel> getPublicPosts(Long userId);
}
