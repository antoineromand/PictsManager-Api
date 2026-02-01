package com.epitech.pictmanager.modules.user_management.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateProfilDto {
    private String description;

    private String profilePicture;

    private String coverPicture;

}
