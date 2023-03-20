package com.epitech.pictmanager.components.user.repositories;

import com.epitech.pictmanager.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileJpaRepository extends JpaRepository<Profile, Long> {
}
