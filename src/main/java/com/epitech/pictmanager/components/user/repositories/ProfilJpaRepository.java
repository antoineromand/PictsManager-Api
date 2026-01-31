package com.epitech.pictmanager.components.user.repositories;

import com.epitech.pictmanager.models.Profil;
import com.epitech.pictmanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilJpaRepository extends JpaRepository<Profil, Long> {
    Profil findProfileByUser(User user);
}
