package com.epitech.pictmanager.modules.media_management.usecases;

import com.epitech.pictmanager.modules.media_management.application.command.UploadMediaCommand;
import com.epitech.pictmanager.modules.media_management.application.services.port.MediaServicePort;
import com.epitech.pictmanager.modules.media_management.application.usecases.UploadMediaUseCase;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.port.MediaRepositoryPort;
import com.epitech.pictmanager.shared.contracts.repositories.UserLookUpRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UploadMediaUseCaseUnitTest {
    @Mock
    private MediaRepositoryPort mediaRepositoryPort;
    @Mock
    private MediaServicePort mediaServicePort;
    @Mock
    private UserLookUpRepositoryPort userLookUpRepositoryPort;

    @InjectMocks
    private UploadMediaUseCase uploadMediaUseCase;

    @Test
    public void shouldNotUploadMediaWhenFilesListIsEmpty() {
        UploadMediaCommand command = new UploadMediaCommand(new ArrayList<>(), UUID.randomUUID().toString());
        Assertions.assertThrows(IllegalArgumentException.class, () -> uploadMediaUseCase.execute(command));
    }

    @Test
    public void shouldNotUploadMediaWhenFilesListIsNull() {
        UploadMediaCommand command = new UploadMediaCommand(null, UUID.randomUUID().toString());
        Assertions.assertThrows(IllegalArgumentException.class, () -> uploadMediaUseCase.execute(command));
    }
}
