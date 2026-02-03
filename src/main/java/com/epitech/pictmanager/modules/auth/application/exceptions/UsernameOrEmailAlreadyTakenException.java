package com.epitech.pictmanager.modules.auth.application.exceptions;

import com.epitech.pictmanager.shared.exceptions.ApplicationLayerException;

public class UsernameOrEmailAlreadyTakenException extends ApplicationLayerException {
    public UsernameOrEmailAlreadyTakenException() {
        super("USERNAME_OR_EMAIL_ALREADY_TAKEN", "Username or email already exists");
    }
}
