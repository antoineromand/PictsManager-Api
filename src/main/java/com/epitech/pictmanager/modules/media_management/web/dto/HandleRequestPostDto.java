package com.epitech.pictmanager.modules.media_management.web.dto;

import java.util.List;

public record HandleRequestPostDto(String description, List<PostMediaDto> postMediaDto) {
}
