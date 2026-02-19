package com.epitech.pictmanager.modules.media_management.infrastructure.repositories.impl;

import com.epitech.pictmanager.modules.media_management.application.read.MediaRowReadModel;
import com.epitech.pictmanager.modules.media_management.application.read.PostRowReadModel;
import com.epitech.pictmanager.modules.media_management.infrastructure.repositories.PostReadRepositoryPort;
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
        String query = """
                SELECT
                  p.id,
                  p.caption,
                  p.created_at,
                  COALESCE(pm.medias, JSON_ARRAY()) AS medias,
                  COALESCE(lk.likes, 0) AS likes
                FROM posts p
                LEFT JOIN (
                  SELECT
                    pa.post_id,
                    JSON_ARRAYAGG(
                      JSON_OBJECT(
                        'mediaId', pa.media_id,
                        'key', m.original_key,
                        'userId', p2.user_id
                      )
                    ) AS medias
                  FROM post_media pa
                  JOIN media m ON m.id = pa.media_id
                  JOIN posts p2 ON p2.id = pa.post_id
                  GROUP BY pa.post_id
                ) pm ON pm.post_id = p.id
                LEFT JOIN (
                  SELECT post_id, COUNT(*) AS likes
                  FROM likes
                  GROUP BY post_id
                ) lk ON lk.post_id = p.id
                WHERE p.user_id = :userId;
                """;

        List<Object[]> rows = em.createNativeQuery(query)
                .setParameter("userId", userId)
                .getResultList();

        return rows.stream().map(
                row -> {
                    Long postId = ((Number) row[0]).longValue();
                    String caption = (String) row[1];
                    LocalDateTime createdAt = null;
                    Object createdObj = row[2];
                    if (createdObj instanceof Timestamp ts) {
                        createdAt = ts.toLocalDateTime();
                    } else if (createdObj instanceof LocalDateTime ldt) {
                        createdAt = ldt;
                    }

                    String mediasJson = row[3] != null ? row[3].toString() : "[]";
                    List<MediaRowReadModel> medias = parseMedias(mediasJson);

                    int likes = row[4] == null ? 0 : ((Number) row[4]).intValue();

                    return new PostRowReadModel(postId, caption, medias, likes, createdAt);
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


