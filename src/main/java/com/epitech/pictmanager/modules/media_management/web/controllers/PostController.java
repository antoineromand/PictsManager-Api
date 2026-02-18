package com.epitech.pictmanager.modules.media_management.web.controllers;

import com.epitech.pictmanager.modules.media_management.application.command.HandlePostCommand;
import com.epitech.pictmanager.modules.media_management.application.usecases.CreatePostUseCase;
import com.epitech.pictmanager.modules.media_management.web.dto.HandleRequestPostDto;
import com.epitech.pictmanager.shared.responses.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("private/api/post")
public class PostController {
    private final CreatePostUseCase createPostUseCase;

    public PostController(CreatePostUseCase createPostUseCase) {
        this.createPostUseCase = createPostUseCase;
    }
    @PostMapping()
    public GenericResponse<Void> post(@AuthenticationPrincipal String userId, @RequestBody HandleRequestPostDto handleRequestPostDto) {
        HandlePostCommand command = new HandlePostCommand(userId,
                handleRequestPostDto.caption(),
                handleRequestPostDto.medias()
        );
        this.createPostUseCase.execute(command);
        return new GenericResponse<>("Post created successfuly", HttpStatus.CREATED.value(), null);
    }
}
