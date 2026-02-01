package com.epitech.pictmanager.modules.user.dto;


import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class UpdateSecurityDto {
    @Getter @Setter
    private String username;
    @Getter  @Setter @NonNull
    private String email;
    @Getter  @Setter @NonNull
    private String password;
    @Getter  @Setter @NonNull
    private Boolean visibility;

}
