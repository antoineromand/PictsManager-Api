package com.epitech.pictmanager.modules.media_management.infrastructure.repositories.impl;

import com.epitech.pictmanager.modules.media_management.domain.Post;
import com.epitech.pictmanager.modules.media_management.infrastructure.models.LikeEntity;
import com.epitech.pictmanager.modules.media_management.infrastructure.models.PostEntity;
import com.epitech.pictmanager.modules.media_management.infrastructure.models.PostMediaEntity;
import com.epitech.pictmanager.modules.media_management.infrastructure.models.embedded.LikeId;
import com.epitech.pictmanager.modules.media_management.infrastructure.models.embedded.PostMediaId;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.PostRepositoryPort;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.jpa.LikesJpaRepository;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.jpa.PostJpaRepository;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.jpa.PostMediaJpaRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class PostRepositoryImplementation implements PostRepositoryPort {

    private final PostJpaRepository postJpaRepository;
    private final PostMediaJpaRepository postMediaJpaRepository;
    private final LikesJpaRepository likesJpaRepository;

    public PostRepositoryImplementation(PostJpaRepository postJpaRepository, PostMediaJpaRepository postMediaJpaRepository, LikesJpaRepository likesJpaRepository) {
        this.postJpaRepository = postJpaRepository;
        this.postMediaJpaRepository = postMediaJpaRepository;
        this.likesJpaRepository = likesJpaRepository;
    }

    @Override
    public void save(Post post) {
        PostEntity postEntity = new PostEntity(null,
                post.getUserId(),
                post.getCaption(),
                LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC)
        );

        var savedEntity = postJpaRepository.save(postEntity);

        this.postMediaJpaRepository.saveAll(
                post.getMediaLinks().stream().map(
                        links -> new PostMediaEntity(PostMediaId.of(savedEntity.getId(), links.getMediaId()), (long) links.getPosition())
                ).toList()
        );
    }

    @Override
    public boolean exists(Long id) {
        return this.postJpaRepository.existsById(id);
    }

    @Override
    public void likePost(Long userId, Long postId) {
        if (this.likesJpaRepository.existsByIdUserIdAndIdPostId(userId, postId)) {
            return;
        }
        this.likesJpaRepository.save(new LikeEntity(new LikeId(postId, userId)));
    }

    @Override
    public void unlikePost(Long userId, Long postId) {
        this.likesJpaRepository.deleteByIdUserIdAndIdPostId(userId, postId);
    }
}
