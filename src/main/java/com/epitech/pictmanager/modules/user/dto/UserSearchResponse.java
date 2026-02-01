package com.epitech.pictmanager.modules.user.dto;

public class UserSearchResponse {
    private String username;
    private Boolean visibility;

    private String profil_picture;

    public UserSearchResponse(String username, Boolean visibility, String profil_picture) {
        this.username = username;
        this.visibility = visibility;
        this.profil_picture = profil_picture;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean aVisibility) {
        visibility = aVisibility;
    }

    public String getProfil_picture() {
        return profil_picture;
    }

    public void setProfil_picture(String profil_picture) {
        this.profil_picture = profil_picture;
    }
}
