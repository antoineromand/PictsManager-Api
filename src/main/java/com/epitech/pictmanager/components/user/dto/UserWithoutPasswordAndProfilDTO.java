package com.epitech.pictmanager.components.user.dto;

import java.util.Date;

public class UserWithoutPasswordAndProfilDTO {
    private String username;
    private String email;
    private Date dateOfBirth;
    private Boolean isPublic;

    public UserWithoutPasswordAndProfilDTO(String username, String email, Date dateOfBirth, Boolean isPublic) {
        this.username = username;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isPublic = isPublic;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }
}