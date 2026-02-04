package com.epitech.pictmanager.modules.user_management.application.exceptions;

import com.epitech.pictmanager.shared.exceptions.ApplicationLayerException;

public class ProfileNotFoundException extends ApplicationLayerException {
    public ProfileNotFoundException() {
        super("PROFIL_NOT_FOUND", "Profile not found.");
    }
}
