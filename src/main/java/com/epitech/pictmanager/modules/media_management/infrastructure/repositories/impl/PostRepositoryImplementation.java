package com.epitech.pictmanager.modules.media_management.infrastructure.repositories.impl;

import com.epitech.pictmanager.modules.media_management.domain.Post;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.PostRepositoryPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

@Component
public class PostRepositoryImplementation implements PostRepositoryPort {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Post save(Post post) {
        return null;
    }
}
