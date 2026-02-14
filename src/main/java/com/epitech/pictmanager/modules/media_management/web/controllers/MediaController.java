package com.epitech.pictmanager.modules.media_management.web.controllers;

import com.epitech.pictmanager.modules.media_management.application.command.UploadMediaCommand;
import com.epitech.pictmanager.modules.media_management.application.read.MediaRowReadModel;
import com.epitech.pictmanager.modules.media_management.application.usecases.GetMediaListUseCase;
import com.epitech.pictmanager.modules.media_management.application.usecases.UploadMediaUseCase;
import com.epitech.pictmanager.modules.media_management.web.dto.UploadMediaRequestDto;
import com.epitech.pictmanager.modules.media_management.web.dto.UploadMediaResponseDto;
import com.epitech.pictmanager.shared.responses.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("private/api/media")
public class MediaController {
    private final UploadMediaUseCase uploadMediaUseCase;
    private final GetMediaListUseCase getMediaListUseCase;

    public MediaController(UploadMediaUseCase uploadMediaUseCase, GetMediaListUseCase getMediaListUseCase) {
        this.uploadMediaUseCase = uploadMediaUseCase;
        this.getMediaListUseCase = getMediaListUseCase;
    }

    @GetMapping("/list")
    public GenericResponse<List<MediaRowReadModel>> list(
            @AuthenticationPrincipal String publicId,
            @RequestParam(name = "offset", defaultValue = "0") int offset,
            @RequestParam(name = "limit", defaultValue = "5") int limit
    ) {
        return new GenericResponse<>(
                null,
                HttpStatus.OK.value(),
                this.getMediaListUseCase.getUserMediaList(publicId, offset, limit)
        );
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
