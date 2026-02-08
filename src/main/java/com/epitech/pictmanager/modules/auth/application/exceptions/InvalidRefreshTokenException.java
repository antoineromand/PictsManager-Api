package com.epitech.pictmanager.modules.auth.application.exceptions;

import com.epitech.pictmanager.shared.exceptions.ApplicationLayerException;

public class InvalidRefreshTokenException extends ApplicationLayerException {
    public InvalidRefreshTokenException() {
        super("INVALID_REFRESH_TOKEN", "Refresh token is invalid");
    }
}
