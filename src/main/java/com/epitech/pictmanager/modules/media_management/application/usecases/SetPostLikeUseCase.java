package com.epitech.pictmanager.modules.media_management.application.usecases;

import com.epitech.pictmanager.modules.media_management.application.exceptions.PostNotFoundException;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.PostRepositoryPort;
import com.epitech.pictmanager.shared.contracts.repositories.UserLookUpRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class SetPostLikeUseCase {
    private final UserLookUpRepositoryPort userLookUpRepositoryPort;
    private final PostRepositoryPort postRepositoryPort;

    public SetPostLikeUseCase(UserLookUpRepositoryPort userLookUpRepositoryPort, PostRepositoryPort postRepositoryPort) {
        this.userLookUpRepositoryPort = userLookUpRepositoryPort;
        this.postRepositoryPort = postRepositoryPort;
    }

    @Transactional
    public void execute(String publicId, Long postId, boolean state) {
        Long userId = this.userLookUpRepositoryPort.getUserIdWithPublicId(publicId);

        if (!this.postRepositoryPort.exists(postId)) {
            throw new PostNotFoundException();
        }

        if (state) this.postRepositoryPort.likePost(userId, postId);
        else this.postRepositoryPort.unlikePost(userId, postId);

    }
}
