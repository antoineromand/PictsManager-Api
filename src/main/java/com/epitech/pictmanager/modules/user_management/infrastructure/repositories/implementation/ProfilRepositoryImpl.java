package com.epitech.pictmanager.modules.user_management.infrastructure.repositories.implementation;

import com.epitech.pictmanager.modules.user_management.domain.UserProfilDomain;
import com.epitech.pictmanager.modules.user_management.infrastructure.models.UserProfil;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.jpa.ProfilJpaRepository;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.ProfilRepositoryPort;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.UserLookUpRepositoryPort;
import com.epitech.pictmanager.shared.contracts.command.CreateProfilCommand;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ProfilRepositoryImpl implements ProfilRepositoryPort {
    private final UserLookUpRepositoryPort userLookUpRepositoryPort;
    private final ProfilJpaRepository jpaRepository;
    public ProfilRepositoryImpl(UserLookUpRepositoryPort userLookUpRepositoryPort, ProfilJpaRepository jpaRepository) {
        this.userLookUpRepositoryPort = userLookUpRepositoryPort;
        this.jpaRepository = jpaRepository;
    }
    @Override
    public void createProfil(UserProfilDomain domain) {
        Long id = this.userLookUpRepositoryPort.getUserIdWithPublicId(domain.getPublicId());
        UserProfil userProfil = UserProfil.toEntity(domain, id);
        this.jpaRepository.save(userProfil);
    }
}
