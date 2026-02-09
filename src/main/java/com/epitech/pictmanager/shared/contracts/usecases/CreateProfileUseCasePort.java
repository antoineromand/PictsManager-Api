package com.epitech.pictmanager.shared.contracts.usecases;

import com.epitech.pictmanager.shared.contracts.command.CreateProfileCommand;

public interface CreateProfileUseCasePort {
    void execute(CreateProfileCommand command);
}
