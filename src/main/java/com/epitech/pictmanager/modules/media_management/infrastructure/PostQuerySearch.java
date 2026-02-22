package com.epitech.pictmanager.modules.media_management.infrastructure;

import com.epitech.pictmanager.shared.enums.WhereClauseFilter;

public final class PostQuerySearch {

    private PostQuerySearch() {
    }

    private static final String SQL_BASE = """
            SELECT
              p.id,
              u.username AS username,
              p.caption,
              p.created_at,
              COALESCE(pm.medias, JSON_ARRAY()) AS medias,
              COALESCE(lk.likes, 0) AS likes,
              up.profile_picture AS profilePicture,
              CASE WHEN vl.post_id IS NULL THEN 0 ELSE 1 END AS likedByViewer
            FROM posts p
            JOIN users u ON u.id = p.user_id
            JOIN user_profile up ON up.user_id = u.id
            
            LEFT JOIN (
              SELECT
                pa.post_id,
                JSON_ARRAYAGG(
                  JSON_OBJECT(
                    'mediaId', pa.media_id,
                    'key', m.original_key,
                    'userId', m.user_id
                  )
                ) AS medias
              FROM post_media pa
              JOIN media m ON m.id = pa.media_id
              GROUP BY pa.post_id
            ) pm ON pm.post_id = p.id
            
            LEFT JOIN (
              SELECT post_id, COUNT(*) AS likes
              FROM likes
              GROUP BY post_id
            ) lk ON lk.post_id = p.id
            
            LEFT JOIN (
              SELECT post_id
              FROM likes
              WHERE user_id = :viewerId
              GROUP BY post_id
            ) vl ON vl.post_id = p.id
            """;

    /* ---------------------------------- WHERE variants ---------------------------------- */

    private static final String WHERE_BY_OWNER = """
            WHERE p.user_id = :userId
            """;

    private static final String WHERE_PUBLIC_EXCEPT_OWNER = """
            WHERE p.user_id <> :userId
              AND u.is_private = false
            """;

    private static final String WHERE_BY_USERNAME = """
            WHERE u.username = :username
            """;

    /* ---------------------------------- ORDER ---------------------------------- */

    private static final String ORDER_BY_NEWEST = """
            ORDER BY p.created_at DESC
            """;

    /* ---------------------------------- BUILD ---------------------------------- */

    public static String build(WhereClauseFilter filter) {
        String whereClause = "";
        switch (filter) {
            case WHERE_BY_OWNER -> whereClause = WHERE_BY_OWNER;
            case WHERE_BY_USERNAME -> whereClause = WHERE_BY_USERNAME;
            case WHERE_PUBLIC_EXCEPT_OWNER -> whereClause = WHERE_PUBLIC_EXCEPT_OWNER;
            default -> whereClause = "";
        }
        return SQL_BASE + "\n" + whereClause + "\n" + ORDER_BY_NEWEST;
    }
}

