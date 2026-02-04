package com.epitech.pictmanager.modules.user_management.infrastructure.repositories.implementation;

import com.epitech.pictmanager.modules.user_management.application.dto.read.UserProfileReadModel;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.UserLookUpRepositoryPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserLookUpRepositoryImpl implements UserLookUpRepositoryPort {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Long getUserIdWithPublicId(String publicId) {
        try {
            System.out.println("LOOKUP USER ID WITH PUBLIC ID: " + publicId);
            return ((Number) em.createNativeQuery(
                            "SELECT id FROM users WHERE public_id = :publicId"
                    )
                    .setParameter("publicId", publicId)
                    .getSingleResult()).longValue();
        } catch (NoResultException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }

    @Override
    public Optional<UserProfileReadModel> getUserAndProfilWithPublicId(String publicId) {
        try {
            var query = """
                SELECT u.username, u.email, u.date_of_birth, p.description, p.profile_picture, p.cover_picture, u.is_public
                FROM users u
                LEFT JOIN user_profile p ON p.user_id = u.id
                WHERE public_id = :publicId
                """;
            Tuple tuple = (Tuple) em.createNativeQuery(query, Tuple.class)
                    .setParameter("publicId", publicId)
                    .getSingleResult();

            return Optional.of(new UserProfileReadModel(
                    tuple.get("username", String.class),
                    tuple.get("email", String.class),
                    tuple.get("date_of_birth", Date.class),
                    tuple.get("description", String.class),
                    tuple.get("profile_picture", String.class),
                    tuple.get("cover_picture", String.class),
                    tuple.get("is_public", Boolean.class))
            );
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }


}
