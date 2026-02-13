package com.epitech.pictmanager.modules.media_management.infrastructure.storage;

import com.epitech.pictmanager.modules.media_management.application.services.port.MediaStoragePort;
import com.epitech.pictmanager.modules.media_management.infrastructure.exceptions.MediaStorageException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Component
public class S3MediaStorageImplementation implements MediaStoragePort {
    private final S3Client s3;
    private final StorageConfigProperties props;

    public S3MediaStorageImplementation(S3Client client, StorageConfigProperties properties) {
        this.s3 = client;
        this.props = properties;
    }

    @Override
    public void upload(MultipartFile file, String key) {
        try {
            s3.putObject(
                    PutObjectRequest.builder()
                            .bucket(props.getBucket())
                            .key(key)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromBytes(file.getBytes())
            );
        } catch (IOException e) {
            throw new MediaStorageException(e);
        }
    }
}
