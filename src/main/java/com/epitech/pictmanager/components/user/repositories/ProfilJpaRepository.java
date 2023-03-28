package com.epitech.pictmanager.components.user.repositories;

import com.epitech.pictmanager.models.Profil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilJpaRepository extends JpaRepository<Profil, Long> {
    Profil findProfileByUser(Long id);


}
