package com.epitech.pictmanager.components.image_manager.repositories;

import com.epitech.pictmanager.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageJpaRepository extends JpaRepository<Image, Long> {
    Image findImageById(Long id);
    Image existsImageById(Long id);

    Image findImageByPath(String path);
    void deleteImageById(Long id);
}
