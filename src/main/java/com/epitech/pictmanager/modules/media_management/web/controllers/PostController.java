package com.epitech.pictmanager.modules.media_management.web.controllers;

import com.epitech.pictmanager.modules.media_management.application.command.HandlePostCommand;
import com.epitech.pictmanager.modules.media_management.application.read.PostRowReadModel;
import com.epitech.pictmanager.modules.media_management.application.usecases.CreatePostUseCase;
import com.epitech.pictmanager.modules.media_management.application.usecases.GetPostFromUserUseCase;
import com.epitech.pictmanager.modules.media_management.application.usecases.SetPostLikeUseCase;
import com.epitech.pictmanager.modules.media_management.web.dto.HandleRequestPostDto;
import com.epitech.pictmanager.modules.media_management.web.dto.SetLikeRequestDto;
import com.epitech.pictmanager.shared.responses.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("private/api/post")
public class PostController {
    private final CreatePostUseCase createPostUseCase;
    private final GetPostFromUserUseCase getPostFromUserUseCase;
    private final SetPostLikeUseCase setPostLikeUseCase;

    public PostController(CreatePostUseCase createPostUseCase, GetPostFromUserUseCase getPostFromUserUseCase, SetPostLikeUseCase setPostLikeUseCase) {
        this.createPostUseCase = createPostUseCase;
        this.getPostFromUserUseCase = getPostFromUserUseCase;
        this.setPostLikeUseCase = setPostLikeUseCase;
    }
    @PostMapping()
    public GenericResponse<Void> post(@AuthenticationPrincipal String publicId, @RequestBody HandleRequestPostDto handleRequestPostDto) {
        HandlePostCommand command = new HandlePostCommand(publicId,
                handleRequestPostDto.caption(),
                handleRequestPostDto.medias()
        );
        this.createPostUseCase.execute(command);
        return new GenericResponse<>("Post created successfuly", HttpStatus.CREATED.value(), null);
    }

    @GetMapping("/list")
    public GenericResponse<List<PostRowReadModel>> list(@AuthenticationPrincipal String publicId) {
        var response = this.getPostFromUserUseCase.execute(publicId);
        return new GenericResponse<>(null, HttpStatus.OK.value(), response);
    }

    @PutMapping("/{postId}/like")
    public GenericResponse<Void> togglePostLike(@AuthenticationPrincipal String publicId, @PathVariable Long postId, @RequestBody SetLikeRequestDto setLikeRequest) {
        this.setPostLikeUseCase.execute(publicId, postId, setLikeRequest.state());
        return new GenericResponse<>("The status of the post's like button has changed.", HttpStatus.OK.value(), null);
    }
}
