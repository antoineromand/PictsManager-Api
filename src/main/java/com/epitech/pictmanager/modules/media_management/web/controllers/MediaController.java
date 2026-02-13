package com.epitech.pictmanager.modules.media_management.web.controllers;

import com.epitech.pictmanager.modules.media_management.application.command.UploadMediaCommand;
import com.epitech.pictmanager.modules.media_management.application.usecases.UploadMediaUseCase;
import com.epitech.pictmanager.modules.media_management.web.dto.UploadMediaRequestDto;
import com.epitech.pictmanager.modules.media_management.web.dto.UploadMediaResponseDto;
import com.epitech.pictmanager.shared.responses.GenericResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("private/api/media")
public class MediaController {
    private final UploadMediaUseCase uploadMediaUseCase;

    public MediaController(UploadMediaUseCase uploadMediaUseCase) {
        this.uploadMediaUseCase = uploadMediaUseCase;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public GenericResponse<List<UploadMediaResponseDto>> upload(
            @AuthenticationPrincipal String publicId,
            @ModelAttribute() UploadMediaRequestDto uploadMediaRequestDto
    ) {
        UploadMediaCommand command = new UploadMediaCommand(
                uploadMediaRequestDto.files(),
                publicId
        );
        var response = uploadMediaUseCase.execute(command);
        return new GenericResponse<>(null, 201, response);
    }
}
