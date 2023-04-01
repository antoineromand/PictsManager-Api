package com.epitech.pictmanager.components.user.dto;


public class UpdateSecurityDto {
    private String username;
    private String email;
    private String password;
    private String newPassword;
    private Boolean isPublic;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public Boolean getPublic() {
        return isPublic;
    }
}
