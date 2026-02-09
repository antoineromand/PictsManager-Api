package com.epitech.pictmanager.modules.user_management.infrastructure.repositories.implementation;

import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.UserLookUpRepositoryPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;


@Repository
@RequiredArgsConstructor
public class UserLookUpRepositoryImpl implements UserLookUpRepositoryPort {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Long getUserIdWithPublicId(String publicId) {
        try {
            return ((Number) em.createNativeQuery(
                            "SELECT id FROM users WHERE public_id = :publicId"
                    )
                    .setParameter("publicId", publicId)
                    .getSingleResult()).longValue();
        } catch (NoResultException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
        }
    }




}
