package com.epitech.pictmanager.modules.media_management.infrastructure.repositories.jpa;

import com.epitech.pictmanager.modules.media_management.infrastructure.models.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaJpaRepository extends JpaRepository<MediaEntity, String> {
}
