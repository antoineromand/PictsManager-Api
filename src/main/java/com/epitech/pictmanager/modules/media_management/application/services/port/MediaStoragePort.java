package com.epitech.pictmanager.modules.media_management.application.services.port;

public interface MediaStoragePort {
    void upload(byte[] bytes, String contentType, String key);
}
