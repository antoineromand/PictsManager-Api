package com.epitech.pictmanager.components.user.dto;

import com.epitech.pictmanager.models.Image;
import com.epitech.pictmanager.models.Profil;

import java.util.Date;
import java.util.Set;

public class UserWithoutPasswordDto {
    private String username;
    private String email;
    private Date dateOfBirth;
    private Boolean isPublic;

    private Profil profil;

    private Set<Image> images;

    public UserWithoutPasswordDto(String username, String email, Date dateOfBirth, Boolean isPublic, Profil profil, Set<Image> images) {
        this.username = username;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.isPublic = isPublic;
        this.profil = profil;
        this.images = images;
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

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
