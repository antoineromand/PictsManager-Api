package com.epitech.pictmanager.modules.user_management.repositories;

import com.epitech.pictmanager.models.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilJpaRepository extends JpaRepository<Profil, Long> {
    Profil findProfileByUserId(Long userId);
}
