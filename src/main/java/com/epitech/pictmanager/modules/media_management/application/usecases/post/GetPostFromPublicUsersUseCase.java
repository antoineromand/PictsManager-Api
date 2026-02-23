package com.epitech.pictmanager.modules.media_management.application.usecases.post;

import com.epitech.pictmanager.modules.media_management.application.read.PostRowReadModel;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.PostReadRepositoryPort;
import com.epitech.pictmanager.shared.contracts.repositories.UserLookUpRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetPostFromPublicUsersUseCase {

    private final PostReadRepositoryPort postReadRepository;
    private final UserLookUpRepositoryPort userLookUpRepository;

    public GetPostFromPublicUsersUseCase(PostReadRepositoryPort postReadRepository, UserLookUpRepositoryPort userLookUpRepository) {
        this.postReadRepository = postReadRepository;
        this.userLookUpRepository = userLookUpRepository;
    }

    public List<PostRowReadModel> execute(String publicId) {
        Long userId = this.userLookUpRepository.getUserIdWithPublicId(publicId);
        return this.postReadRepository.getPublicPosts(userId);
    }
}
