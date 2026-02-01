package com.epitech.pictmanager.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Date;

@Entity()
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue
    @Getter  @Setter
    private Long id;

    @Column(nullable = false)
    @Getter  @Setter
    private String description;

    @Column(nullable = false)
    @Getter  @Setter
    private String name;

    @Column(nullable = false)
    @Getter  @Setter
    private String path;

    @Getter  @Setter
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonBackReference
    @Getter  @Setter
    private User user;

}
