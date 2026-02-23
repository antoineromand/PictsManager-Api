package com.epitech.pictmanager.modules.media_management.usecases;

import com.epitech.pictmanager.modules.media_management.application.command.HandlePostCommand;
import com.epitech.pictmanager.modules.media_management.application.exceptions.MediaNotFoundException;
import com.epitech.pictmanager.modules.media_management.application.exceptions.MediaPermissionException;
import com.epitech.pictmanager.modules.media_management.application.read.MediaRowReadModel;
import com.epitech.pictmanager.modules.media_management.application.usecases.post.CreatePostUseCase;
import com.epitech.pictmanager.modules.media_management.domain.Post;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.MediaReadRepositoryPort;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.PostRepositoryPort;
import com.epitech.pictmanager.shared.contracts.repositories.UserLookUpRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreatePostUseCaseUnitTest {
    @Mock
    private MediaReadRepositoryPort mediaReadRepositoryPort;
    @Mock
    private UserLookUpRepositoryPort userLookUpRepositoryPort;
    @Mock
    private PostRepositoryPort postRepositoryPort;
    @InjectMocks
    private CreatePostUseCase createPostUseCase;

    @Test
    public void shouldThrowExceptionWhenMediaIsNotFound() {
        HandlePostCommand command = new HandlePostCommand(
                UUID.randomUUID().toString(),
                "Test publication",
                List.of("xxx-xxxx-xxxx-xxx1", "xxx-xxxx-xxxx-xxx2")
        );

        when(this.userLookUpRepositoryPort.getUserIdWithPublicId(command.userId())).thenReturn(1L);

        var expectedMediaResult = new ArrayList<MediaRowReadModel>(
                Collections.singleton(new MediaRowReadModel(command.mediasId().getFirst(), "test/xxx-xxxx-xxxx-xxx1/original.jpg", 1L))
        );

        when(this.mediaReadRepositoryPort.getMediasById(command.mediasId())).thenReturn(expectedMediaResult);

        assertThrows(MediaNotFoundException.class, () -> createPostUseCase.execute(command));

        verify(userLookUpRepositoryPort).getUserIdWithPublicId(command.userId());
        verify(mediaReadRepositoryPort).getMediasById(command.mediasId());
        verify(postRepositoryPort, never()).save(any(Post.class));
    }

    @Test
    public void shouldThrowExceptionWhenUserDoNotHaveThePermissionOnMedia() {
        HandlePostCommand command = new HandlePostCommand(
                UUID.randomUUID().toString(),
                "Test publication",
                List.of("xxx-xxxx-xxxx-xxx1", "xxx-xxxx-xxxx-xxx2")
        );

        when(this.userLookUpRepositoryPort.getUserIdWithPublicId(command.userId())).thenReturn(1L);

        var expectedMediaResult = new ArrayList<MediaRowReadModel>();
        command.mediasId().forEach(id -> expectedMediaResult.add(new MediaRowReadModel(id, "test", 3L)));

        when(this.mediaReadRepositoryPort.getMediasById(command.mediasId())).thenReturn(expectedMediaResult);

        assertThrows(MediaPermissionException.class, () -> createPostUseCase.execute(command));

        verify(userLookUpRepositoryPort).getUserIdWithPublicId(command.userId());
        verify(mediaReadRepositoryPort).getMediasById(command.mediasId());
        verify(postRepositoryPort, never()).save(any(Post.class));
    }

    @Test
    public void shouldCreatePost() {
        HandlePostCommand command = new HandlePostCommand(
                UUID.randomUUID().toString(),
                "Test publication",
                List.of("xxx-xxxx-xxxx-xxx1", "xxx-xxxx-xxxx-xxx2")
        );

        when(this.userLookUpRepositoryPort.getUserIdWithPublicId(command.userId())).thenReturn(1L);

        var expectedMediaResult = new ArrayList<MediaRowReadModel>();
        command.mediasId().forEach(id -> expectedMediaResult.add(new MediaRowReadModel(id, "test", 1L)));

        when(this.mediaReadRepositoryPort.getMediasById(command.mediasId())).thenReturn(expectedMediaResult);

        createPostUseCase.execute(command);

        var captor = ArgumentCaptor.forClass(Post.class);
        verify(this.postRepositoryPort).save(captor.capture());

        var post = captor.getValue();

        assertEquals("Test publication", post.getCaption());
        assertEquals(2, post.getMediaLinks().size());

        verify(this.userLookUpRepositoryPort).getUserIdWithPublicId(command.userId());
        verify(this.mediaReadRepositoryPort).getMediasById(command.mediasId());
        verify(this.postRepositoryPort).save(post);

    }
}
