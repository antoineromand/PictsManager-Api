package com.epitech.pictmanager.modules.media_management.infrastructure.storage;

import com.epitech.pictmanager.modules.media_management.application.services.port.MediaStoragePort;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Component
public class S3MediaStorageImplementation implements MediaStoragePort {
    private final S3Client s3;
    private final StorageConfigProperties props;

    public S3MediaStorageImplementation(S3Client client, StorageConfigProperties properties) {
        this.s3 = client;
        this.props = properties;
    }

    @Override
    public void upload(byte[] bytes, String contentType, String key) {
        String ct = (contentType != null && !contentType.isBlank())
                ? contentType
                : "application/octet-stream";

        s3.putObject(
                PutObjectRequest.builder()
                        .bucket(props.getBucket())
                        .key(key)
                        .contentType(ct)
                        .build(),
                RequestBody.fromBytes(bytes)
        );
    }
}
