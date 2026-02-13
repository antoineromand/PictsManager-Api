package com.epitech.pictmanager.modules.media_management.usecases;

import com.epitech.pictmanager.modules.media_management.application.command.UploadMediaCommand;
import com.epitech.pictmanager.modules.media_management.application.services.MediaDimension;
import com.epitech.pictmanager.modules.media_management.application.services.port.MediaServicePort;
import com.epitech.pictmanager.modules.media_management.application.services.port.MediaStoragePort;
import com.epitech.pictmanager.modules.media_management.application.usecases.UploadMediaUseCase;
import com.epitech.pictmanager.modules.media_management.domain.Media;
import com.epitech.pictmanager.modules.media_management.domain.MediaStatus;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.port.MediaRepositoryPort;
import com.epitech.pictmanager.modules.media_management.web.dto.UploadMediaResponseDto;
import com.epitech.pictmanager.shared.contracts.repositories.UserLookUpRepositoryPort;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UploadMediaUseCaseUnitTest {
    @Mock
    private MediaRepositoryPort mediaRepositoryPort;
    @Mock
    private MediaServicePort mediaServicePort;
    @Mock
    private UserLookUpRepositoryPort userLookUpRepositoryPort;
    @Mock
    private MediaStoragePort mediaStoragePort;

    @InjectMocks
    private UploadMediaUseCase uploadMediaUseCase;

    @Test
    public void shouldNotUploadMediaWhenFilesListIsEmpty() {
        UploadMediaCommand command = new UploadMediaCommand(new ArrayList<>(), UUID.randomUUID().toString());
        Assertions.assertThrows(IllegalArgumentException.class, () -> uploadMediaUseCase.execute(command));
        verifyNoInteractions(userLookUpRepositoryPort, mediaServicePort, mediaRepositoryPort);

    }

    @Test
    public void shouldNotUploadMediaWhenFilesListIsNull() {
        UploadMediaCommand command = new UploadMediaCommand(null, UUID.randomUUID().toString());
        Assertions.assertThrows(IllegalArgumentException.class, () -> uploadMediaUseCase.execute(command));
        verifyNoInteractions(userLookUpRepositoryPort, mediaServicePort, mediaRepositoryPort);
    }

    @Test
    public void shouldNotUploadMediaIfUserIdDoesNotExist() {
        MultipartFile file = mock(MultipartFile.class);
        List<MultipartFile> files = new ArrayList<>();
        files.add(file);
        UploadMediaCommand command = new UploadMediaCommand(files, UUID.randomUUID().toString());

        when(this.userLookUpRepositoryPort.getUserIdWithPublicId(command.publicId())).thenThrow(NoResultException.class);
        Assertions.assertThrows(NoResultException.class, () -> uploadMediaUseCase.execute(command));

        verify(userLookUpRepositoryPort).getUserIdWithPublicId(command.publicId());
        verifyNoInteractions(mediaServicePort, mediaRepositoryPort);
    }

    @Test
    public void shouldUploadMediaAndReturnReadyResponse() {
        Long userId = 1L;
        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("filename.jpg");
        when(file.getContentType()).thenReturn("image/jpeg");

        UploadMediaCommand command = new UploadMediaCommand(List.of(file), UUID.randomUUID().toString());
        when(this.userLookUpRepositoryPort.getUserIdWithPublicId(command.publicId())).thenReturn(userId);

        MediaDimension dim = new MediaDimension(800, 600);
        when(mediaServicePort.extractDimensions(file)).thenReturn(dim);

        ArgumentCaptor<Media> mediaCaptor = ArgumentCaptor.forClass(Media.class);

        List<UploadMediaResponseDto> result = uploadMediaUseCase.execute(command);

        assertEquals(1, result.size());
        UploadMediaResponseDto dto = result.getFirst();
        assertEquals(MediaStatus.READY.name(), dto.status());
        assertNull(dto.error());
        assertNotNull(dto.mediaId());

        verify(userLookUpRepositoryPort).getUserIdWithPublicId(command.publicId());
        verify(mediaRepositoryPort, times(2)).save(mediaCaptor.capture());

        List<Media> savedMedias = mediaCaptor.getAllValues();
        assertEquals(2, savedMedias.size());

        Media mediaAtFirstSave = savedMedias.getFirst();

        assertNotNull(mediaAtFirstSave.originalKey());
        assertTrue(mediaAtFirstSave.originalKey().contains("/original"));
        assertTrue(mediaAtFirstSave.originalKey().endsWith(".jpg"));

        verify(mediaStoragePort).upload(file, mediaAtFirstSave.originalKey());
        verify(mediaServicePort).extractDimensions(file);

        verifyNoMoreInteractions(mediaServicePort);

    }


}
