package com.epitech.pictmanager.modules.user_management.infrastructure.repositories.jpa;

import com.epitech.pictmanager.modules.user_management.infrastructure.models.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileJpaRepository extends JpaRepository<UserProfile, Long> {


}
