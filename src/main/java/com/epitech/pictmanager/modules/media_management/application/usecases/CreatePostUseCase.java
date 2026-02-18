package com.epitech.pictmanager.modules.media_management.application.usecases;

import com.epitech.pictmanager.modules.media_management.application.command.HandlePostCommand;
import com.epitech.pictmanager.modules.media_management.application.exceptions.MediaNotFoundException;
import com.epitech.pictmanager.modules.media_management.application.exceptions.MediaPermissionException;
import com.epitech.pictmanager.modules.media_management.application.read.MediaRowReadModel;
import com.epitech.pictmanager.modules.media_management.domain.Post;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.MediaReadRepositoryPort;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.PostRepositoryPort;
import com.epitech.pictmanager.shared.contracts.repositories.UserLookUpRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CreatePostUseCase {
    private final MediaReadRepositoryPort mediaReadRepositoryPort;
    private final UserLookUpRepositoryPort userLookUpRepositoryPort;
    private final PostRepositoryPort postRepositoryPort;

    public CreatePostUseCase(MediaReadRepositoryPort mediaReadRepositoryPort,
                             UserLookUpRepositoryPort userLookUpRepositoryPort,
                             PostRepositoryPort postRepositoryPort) {
        this.mediaReadRepositoryPort = mediaReadRepositoryPort;
        this.userLookUpRepositoryPort = userLookUpRepositoryPort;
        this.postRepositoryPort = postRepositoryPort;
    }

    @Transactional
    public void execute(HandlePostCommand command) {
        Long id = this.userLookUpRepositoryPort.getUserIdWithPublicId(command.userId());

        this.isMediaIdMissing(id, command.mediasId());

        Post post = new Post(null, id, command.caption());
        command.mediasId().forEach(post::attach);

        this.postRepositoryPort.save(post);

    }

    private void isMediaIdMissing(Long userId, List<String> ids) {
        List<MediaRowReadModel> models = this.mediaReadRepositoryPort.getMediasById(ids);
        if (models.size() != ids.size()) {
            throw new MediaNotFoundException();
        }
        models.forEach(media -> {
            if (!Objects.equals(media.userId(), userId)) {
                throw new MediaPermissionException();
            }
        });
    }
}
