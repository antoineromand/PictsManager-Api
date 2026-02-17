package com.epitech.pictmanager.modules.media_management.web.controllers;

import com.epitech.pictmanager.modules.media_management.application.command.HandlePostCommand;
import com.epitech.pictmanager.modules.media_management.web.dto.HandleRequestPostDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("private/api/post")
public class PostController {
    @PostMapping()
    public HandleRequestPostDto post(@AuthenticationPrincipal String userId, @RequestBody HandleRequestPostDto handleRequestPostDto) {
        Map<String, Integer> medias = new HashMap<>();
        handleRequestPostDto.postMediaDto().forEach(media -> {
            medias.put(media.mediaId(), media.position());
        });
        HandlePostCommand command = new HandlePostCommand(userId, handleRequestPostDto.description(), medias);
        return null;
    }
}
