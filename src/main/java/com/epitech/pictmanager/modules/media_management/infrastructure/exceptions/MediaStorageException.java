package com.epitech.pictmanager.modules.media_management.infrastructure.exceptions;

public class MediaStorageException extends RuntimeException {
    public MediaStorageException(Throwable cause) {
        super("Error while uploading file in storage.");
    }
}
