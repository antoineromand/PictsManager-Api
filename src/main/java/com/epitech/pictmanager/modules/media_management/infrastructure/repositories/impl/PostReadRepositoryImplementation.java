package com.epitech.pictmanager.modules.media_management.infrastructure.repositories.impl;

import com.epitech.pictmanager.modules.media_management.application.read.MediaRowReadModel;
import com.epitech.pictmanager.modules.media_management.application.read.PostRowReadModel;
import com.epitech.pictmanager.modules.media_management.infrastructure.PostQuerySearch;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.PostReadRepositoryPort;
import com.epitech.pictmanager.shared.enums.WhereClauseFilter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Component
public class PostReadRepositoryImplementation implements PostReadRepositoryPort {
    @PersistenceContext
    private EntityManager em;

    private final ObjectMapper objectMapper;

    public PostReadRepositoryImplementation(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<PostRowReadModel> getPostsByUserId(Long userId) {
        String query = PostQuerySearch.build(WhereClauseFilter.WHERE_BY_OWNER);

        List<Object[]> rows = em.createNativeQuery(query)
                .setParameter("userId", userId)
                .setParameter("viewerId", userId)
                .getResultList();

        return rows.stream().map(
                row -> {
                    Long postId = ((Number) row[0]).longValue();
                    String author = (String) row[1];
                    String caption = (String) row[2];
                    LocalDateTime createdAt = null;
                    Object createdObj = row[3];
                    if (createdObj instanceof Timestamp ts) {
                        createdAt = ts.toLocalDateTime();
                    } else if (createdObj instanceof LocalDateTime ldt) {
                        createdAt = ldt;
                    }

                    String mediasJson = row[4] != null ? row[4].toString() : "[]";
                    List<MediaRowReadModel> medias = parseMedias(mediasJson);

                    int likes = row[5] == null ? 0 : ((Number) row[5]).intValue();

                    String profilePicture = (String) row[6];

                    boolean isLiked = (int) row[7] != 0;

                    return new PostRowReadModel(postId, author, profilePicture, caption, medias, likes, createdAt, isLiked);
                }
        ).toList();
    }

    private List<MediaRowReadModel> parseMedias(String mediasJson) {
        try {
            return objectMapper.readValue(
                    mediasJson,
                    new TypeReference<List<MediaRowReadModel>>() {
                    }
            );
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }
}


