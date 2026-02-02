package com.epitech.pictmanager.modules.auth.web.dto;

import com.epitech.pictmanager.modules.auth.application.command.LoginCommand;
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

    public LoginCommand toCommand() {
        return new LoginCommand(username, password);
    }
}
