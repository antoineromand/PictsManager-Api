package com.epitech.pictmanager.modules.media_management.infrastructure.repositories.jpa;

import com.epitech.pictmanager.modules.media_management.infrastructure.models.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {

}
