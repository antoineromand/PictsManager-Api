package com.epitech.pictmanager.modules.media_management.application.exceptions;

import com.epitech.pictmanager.shared.exceptions.ApplicationLayerException;

public class MediaPermissionException extends ApplicationLayerException {
    public MediaPermissionException() {
        super("MEDIA_PERMISSION_REFUSED", "Media permission refused");
    }
}
