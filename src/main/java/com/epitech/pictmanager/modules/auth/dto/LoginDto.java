package com.epitech.pictmanager.modules.auth.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class LoginDto {
    @Getter  @Setter @NonNull
    private String username;
    @Getter  @Setter @NonNull
    private String password;
}
