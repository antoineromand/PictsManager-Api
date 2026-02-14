package com.epitech.pictmanager.modules.media_management.usecases;

import com.epitech.pictmanager.modules.media_management.application.command.UploadMediaCommand;
import com.epitech.pictmanager.modules.media_management.application.services.MediaDimension;
import com.epitech.pictmanager.modules.media_management.application.services.PreparedMedia;
import com.epitech.pictmanager.modules.media_management.application.services.port.MediaServicePort;
import com.epitech.pictmanager.modules.media_management.application.usecases.UploadMediaUseCase;
import com.epitech.pictmanager.modules.media_management.domain.Media;
import com.epitech.pictmanager.modules.media_management.domain.MediaStatus;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.port.MediaRepositoryPort;
import com.epitech.pictmanager.modules.media_management.web.dto.UploadMediaResponseDto;
import com.epitech.pictmanager.shared.contracts.repositories.UserLookUpRepositoryPort;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UploadMediaUseCaseUnitTest {

    @Mock
    private MediaRepositoryPort mediaRepositoryPort;
    @Mock
    private MediaServicePort mediaServicePort;
    @Mock
    private UserLookUpRepositoryPort userLookUpRepositoryPort;

    @InjectMocks
    private UploadMediaUseCase uploadMediaUseCase;

    @Test
    void shouldThrowWhenFilesListIsNull() {
        UploadMediaCommand command = new UploadMediaCommand(null, UUID.randomUUID().toString());

        assertThrows(IllegalArgumentException.class, () -> uploadMediaUseCase.execute(command));

        verifyNoInteractions(userLookUpRepositoryPort, mediaServicePort, mediaRepositoryPort);
    }

    @Test
    void shouldThrowWhenFilesListIsEmpty() {
        UploadMediaCommand command = new UploadMediaCommand(List.of(), UUID.randomUUID().toString());

        assertThrows(IllegalArgumentException.class, () -> uploadMediaUseCase.execute(command));

        verifyNoInteractions(userLookUpRepositoryPort, mediaServicePort, mediaRepositoryPort);
    }

    @Test
    void shouldPropagateWhenUserIdLookupFails() {
        MultipartFile file = mock(MultipartFile.class);
        UploadMediaCommand command = new UploadMediaCommand(List.of(file), UUID.randomUUID().toString());

        when(userLookUpRepositoryPort.getUserIdWithPublicId(command.publicId()))
                .thenThrow(NoResultException.class);

        assertThrows(NoResultException.class, () -> uploadMediaUseCase.execute(command));

        verify(userLookUpRepositoryPort).getUserIdWithPublicId(command.publicId());
        verifyNoInteractions(mediaServicePort, mediaRepositoryPort);
    }

    @Test
    void shouldUploadOneMediaAndReturnReady() {
        Long userId = 1L;

        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("photo.jpg");
        when(file.getContentType()).thenReturn("image/jpeg");

        UploadMediaCommand command = new UploadMediaCommand(List.of(file), UUID.randomUUID().toString());

        when(userLookUpRepositoryPort.getUserIdWithPublicId(command.publicId())).thenReturn(userId);

        byte[] bytes = "img".getBytes();
        PreparedMedia prepared = new PreparedMedia(bytes, "image/jpeg", new MediaDimension(800, 600));
        when(mediaServicePort.prepare(file)).thenReturn(prepared);
        doNothing().when(mediaServicePort).addToStorage(any(byte[].class), anyString(), anyString());

        ArgumentCaptor<Media> mediaCaptor = ArgumentCaptor.forClass(Media.class);

        List<UploadMediaResponseDto> res = uploadMediaUseCase.execute(command);

        assertEquals(1, res.size());
        UploadMediaResponseDto dto = res.getFirst();
        assertNotNull(dto.mediaId());
        assertEquals(MediaStatus.READY.name(), dto.status());
        assertNull(dto.error());

        verify(userLookUpRepositoryPort).getUserIdWithPublicId(command.publicId());
        verify(mediaServicePort).prepare(file);

        verify(mediaRepositoryPort, times(2)).save(mediaCaptor.capture());
        Media firstSave = mediaCaptor.getAllValues().getFirst();
        assertEquals(MediaStatus.READY, firstSave.status());

        assertTrue(firstSave.originalKey().startsWith("uploads/"));
        assertTrue(firstSave.originalKey().contains("/original"));
        assertTrue(firstSave.originalKey().endsWith(".jpg"));

        verify(mediaServicePort).addToStorage(eq(bytes), eq("image/jpeg"), eq(firstSave.originalKey()));

        Media secondSave = mediaCaptor.getAllValues().getLast();
        assertEquals(MediaStatus.READY, secondSave.status());
        assertEquals(800, secondSave.width());
        assertEquals(600, secondSave.height());
    }

    @Test
    void shouldMarkFailedWhenPrepareThrows() {
        Long userId = 1L;

        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("photo.jpg");
        when(file.getContentType()).thenReturn("image/jpeg");

        UploadMediaCommand command = new UploadMediaCommand(List.of(file), UUID.randomUUID().toString());

        when(userLookUpRepositoryPort.getUserIdWithPublicId(command.publicId())).thenReturn(userId);
        when(mediaServicePort.prepare(file)).thenThrow(new IllegalArgumentException("invalid image"));

        ArgumentCaptor<Media> mediaCaptor = ArgumentCaptor.forClass(Media.class);

        List<UploadMediaResponseDto> res = uploadMediaUseCase.execute(command);

        assertEquals(1, res.size());
        UploadMediaResponseDto dto = res.getFirst();
        assertEquals(MediaStatus.FAILED.name(), dto.status());
        assertNotNull(dto.error());

        verify(mediaServicePort, never()).addToStorage(any(), anyString(), anyString());
        verify(mediaRepositoryPort, times(2)).save(mediaCaptor.capture());

        Media secondSave = mediaCaptor.getAllValues().getLast();
        assertEquals(MediaStatus.FAILED, secondSave.status());
    }

    @Test
    void shouldMarkFailedWhenAddToStorageThrows() {
        Long userId = 1L;

        MultipartFile file = mock(MultipartFile.class);
        when(file.getOriginalFilename()).thenReturn("photo.png");
        when(file.getContentType()).thenReturn("image/png");

        UploadMediaCommand command = new UploadMediaCommand(List.of(file), UUID.randomUUID().toString());

        when(userLookUpRepositoryPort.getUserIdWithPublicId(command.publicId())).thenReturn(userId);

        byte[] bytes = "img".getBytes();
        PreparedMedia prepared = new PreparedMedia(bytes, "image/png", new MediaDimension(10, 20));
        when(mediaServicePort.prepare(file)).thenReturn(prepared);

        doThrow(new RuntimeException("upload error"))
                .when(mediaServicePort).addToStorage(any(byte[].class), anyString(), anyString());

        ArgumentCaptor<Media> mediaCaptor = ArgumentCaptor.forClass(Media.class);

        List<UploadMediaResponseDto> res = uploadMediaUseCase.execute(command);

        assertEquals(1, res.size());
        UploadMediaResponseDto dto = res.getFirst();
        assertEquals(MediaStatus.FAILED.name(), dto.status());

        verify(mediaRepositoryPort, times(2)).save(mediaCaptor.capture());
        Media firstSave = mediaCaptor.getAllValues().getFirst();

        verify(mediaServicePort).addToStorage(eq(bytes), eq("image/png"), eq(firstSave.originalKey()));

        Media secondSave = mediaCaptor.getAllValues().getLast();
        assertEquals(MediaStatus.FAILED, secondSave.status());
    }

    @Test
    void shouldHandleMultipleFilesAndLookupUserOnlyOnce() {
        Long userId = 1L;

        MultipartFile f1 = mock(MultipartFile.class);
        when(f1.getOriginalFilename()).thenReturn("a.jpg");
        when(f1.getContentType()).thenReturn("image/jpeg");

        MultipartFile f2 = mock(MultipartFile.class);
        when(f2.getOriginalFilename()).thenReturn("b.jpg");
        when(f2.getContentType()).thenReturn("image/jpeg");

        MultipartFile f3 = mock(MultipartFile.class);
        when(f3.getOriginalFilename()).thenReturn("c.jpg");
        when(f3.getContentType()).thenReturn("image/jpeg");

        UploadMediaCommand command = new UploadMediaCommand(List.of(f1, f2, f3), UUID.randomUUID().toString());
        when(userLookUpRepositoryPort.getUserIdWithPublicId(command.publicId())).thenReturn(userId);

        when(mediaServicePort.prepare(any(MultipartFile.class)))
                .thenAnswer(inv -> new PreparedMedia(
                        "img".getBytes(), "image/jpeg", new MediaDimension(1, 1)
                ));
        doNothing().when(mediaServicePort).addToStorage(any(byte[].class), anyString(), anyString());

        List<UploadMediaResponseDto> res = uploadMediaUseCase.execute(command);

        assertEquals(3, res.size());
        verify(userLookUpRepositoryPort, times(1)).getUserIdWithPublicId(command.publicId());
        verify(mediaRepositoryPort, times(3 * 2)).save(any(Media.class)); // 2 saves par fichier
    }
}
