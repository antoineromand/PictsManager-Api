package com.epitech.pictmanager.shared.contracts.usecases;

import com.epitech.pictmanager.shared.contracts.command.CreateProfilCommand;

public interface CreateProfilUseCasePort {
    void execute(CreateProfilCommand command);
}
