package com.epitech.pictmanager.components.user.dto;


import javax.validation.constraints.Null;

public class UpdateSecurityDto {
    private String username;
    private String email;
    @Null
    private String password;
    private Boolean visibility;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public Boolean getVisibility() {
        return visibility;
    }
}
