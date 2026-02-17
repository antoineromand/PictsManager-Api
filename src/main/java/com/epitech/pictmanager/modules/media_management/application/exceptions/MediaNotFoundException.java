package com.epitech.pictmanager.modules.media_management.application.exceptions;

import com.epitech.pictmanager.shared.exceptions.ApplicationLayerException;

public class MediaNotFoundException extends ApplicationLayerException {

    public MediaNotFoundException() {
        super("MEDIA_NOT_FOUND", "Media not found");
    }
}
