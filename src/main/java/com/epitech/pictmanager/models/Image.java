package com.epitech.pictmanager.models;

import javax.persistence.*;

@Entity()
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String date;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
