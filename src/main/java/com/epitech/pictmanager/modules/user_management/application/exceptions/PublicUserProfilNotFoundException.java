package com.epitech.pictmanager.modules.user_management.application.exceptions;

import com.epitech.pictmanager.shared.exceptions.ApplicationLayerException;

public class PublicUserProfilNotFoundException extends ApplicationLayerException {
    public PublicUserProfilNotFoundException() {
        super("PUBLIC_USER_PROFIL_NOT_FOUND", "Public profile not found.");
    }
}
