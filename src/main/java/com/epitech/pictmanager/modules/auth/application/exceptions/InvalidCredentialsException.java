package com.epitech.pictmanager.modules.auth.application.exceptions;

import com.epitech.pictmanager.shared.exceptions.ApplicationLayerException;

public class InvalidCredentialsException extends ApplicationLayerException {
    public InvalidCredentialsException() {
        super("INVALID_CREDENTIALS", "Username or password is invalid");
    }
}
