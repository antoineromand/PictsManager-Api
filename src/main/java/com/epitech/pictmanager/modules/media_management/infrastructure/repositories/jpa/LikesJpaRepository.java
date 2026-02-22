package com.epitech.pictmanager.modules.media_management.infrastructure.repositories.jpa;

import com.epitech.pictmanager.modules.media_management.infrastructure.models.LikeEntity;
import com.epitech.pictmanager.modules.media_management.infrastructure.models.embedded.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesJpaRepository extends JpaRepository<LikeEntity, LikeId> {
    void deleteByIdUserIdAndIdPostId(Long userId, Long postId);

    boolean existsByIdUserIdAndIdPostId(Long userId, Long postId);
}
