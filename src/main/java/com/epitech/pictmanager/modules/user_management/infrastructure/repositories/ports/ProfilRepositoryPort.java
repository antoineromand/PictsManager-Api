package com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports;

import com.epitech.pictmanager.modules.user_management.domain.UserProfilDomain;
import com.epitech.pictmanager.shared.contracts.command.CreateProfilCommand;

import java.util.UUID;

public interface ProfilRepositoryPort {
    void createProfil(UserProfilDomain profil);
}
