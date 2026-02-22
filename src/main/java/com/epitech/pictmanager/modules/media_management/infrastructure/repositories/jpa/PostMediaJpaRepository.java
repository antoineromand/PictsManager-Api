package com.epitech.pictmanager.modules.media_management.infrastructure.repositories.jpa;

import com.epitech.pictmanager.modules.media_management.infrastructure.models.PostMediaEntity;
import com.epitech.pictmanager.modules.media_management.infrastructure.models.embedded.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostMediaJpaRepository extends JpaRepository<PostMediaEntity, LikeId> {

}
