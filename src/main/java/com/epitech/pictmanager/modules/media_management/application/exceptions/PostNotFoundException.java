package com.epitech.pictmanager.modules.media_management.application.exceptions;

import com.epitech.pictmanager.shared.exceptions.ApplicationLayerException;

public class PostNotFoundException extends ApplicationLayerException {
    public PostNotFoundException() {
        super("POST_NOT_FOUND", "Post not found");
    }
}
