package com.epitech.pictmanager.modules.auth.infrastructure.models;

import com.epitech.pictmanager.modules.auth.domain.UserDomain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity()
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false, unique = true, updatable = false, insertable = false, name="public_id")
    private String publicId;

    @Column(unique = true, nullable = false)
    @Getter
    @Setter
    private String username;

    @Column(nullable = false)
    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    @Column(unique = true, nullable = false)
    private String email;

    @Getter
    @Setter
    @Column(nullable = false, name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(nullable = false, name = "is_banned")
    @Getter
    @Setter
    private Boolean isBanned;

    @Column(nullable = false, name = "is_public")
    @Getter
    @Setter
    private Boolean isPublic;

    public User() {

    }

    public User(Long id, String publicId, String username, String password, String email, LocalDate dateOfBirth, boolean isBanned, boolean isPublic) {
        this.id = id;
        this.publicId = publicId;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.isBanned = isBanned;
        this.isPublic = isPublic;
    }

    public UserDomain toDomain() {
        return new UserDomain(
                this.getPublicId(),
                this.getUsername(),
                this.getPassword(),
                this.getEmail(),
                this.getDateOfBirth(),
                this.getIsBanned(),
                this.getIsPublic()
        );
    }

    public static User fromDomain(UserDomain domain, Long id) {
        return new User(
                id,
                domain.getUserId(),
                domain.getUsername(),
                domain.getPassword(),
                domain.getEmail(),
                domain.getBirthDate(),
                domain.isBanned(),
                domain.isPublic()
        );
    }

}
