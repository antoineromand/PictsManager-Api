package com.epitech.pictmanager.modules.user_management.infrastructure.repositories.implementation;

import com.epitech.pictmanager.modules.user_management.application.dto.read.UserProfileReadModel;
import com.epitech.pictmanager.modules.user_management.application.dto.read.UserProfileSearchReadModel;
import com.epitech.pictmanager.modules.user_management.domain.UserProfileDomain;
import com.epitech.pictmanager.modules.user_management.infrastructure.models.UserProfile;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.jpa.ProfileJpaRepository;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.ProfileRepositoryPort;
import com.epitech.pictmanager.modules.user_management.infrastructure.repositories.ports.UserLookUpRepositoryPort;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProfileRepositoryImpl implements ProfileRepositoryPort {
    @PersistenceContext
    private EntityManager em;

    private final UserLookUpRepositoryPort userLookUpRepositoryPort;
    private final ProfileJpaRepository jpaRepository;
    public ProfileRepositoryImpl(UserLookUpRepositoryPort userLookUpRepositoryPort, ProfileJpaRepository jpaRepository) {
        this.userLookUpRepositoryPort = userLookUpRepositoryPort;
        this.jpaRepository = jpaRepository;
    }
    @Override
    public void createProfile(UserProfileDomain domain) {
        Long id = this.userLookUpRepositoryPort.getUserIdWithPublicId(domain.getPublicId());
        UserProfile userProfile = UserProfile.toEntity(domain, id);
        this.jpaRepository.save(userProfile);
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

    @Override
    public List<UserProfileSearchReadModel> getUsersAndProfilsWithSearchInput(String searchInput, int limit, int offset) {
        var query = """
                        SELECT
                            u.username        AS username,
                            p.profile_picture AS profile_picture,
                            p.description     AS description,
                            u.is_public       AS is_public
                        FROM users u
                        LEFT JOIN user_profile p ON p.user_id = u.id
                        WHERE u.username LIKE :searchInput
                        LIMIT :limit OFFSET :offset
                        """;
        @SuppressWarnings("unchecked")
        List<Tuple> tuples = (List<Tuple>) em.createNativeQuery(query, Tuple.class)
                .setParameter("searchInput", "%" + searchInput + "%")
                .setParameter("limit", limit)
                .setParameter("offset", offset)
                .getResultList();

        return tuples.stream()
                .map(t -> new UserProfileSearchReadModel(
                        t.get("username", String.class),
                        t.get("profile_picture", String.class),
                        t.get("is_public", Boolean.class),
                        t.get("description", String.class)
                ))
                .toList();
    }

    @Override
    public int getTotalFromSearch(String searchInput) {
        var query = """
                        SELECT COUNT(*) FROM users u
                        WHERE u.username LIKE :searchInput
                        """;
        Long count = (Long) em.createNativeQuery(query, Long.class).setParameter("searchInput", "%" + searchInput + "%").getSingleResult();
        return count.intValue();
    }
}
