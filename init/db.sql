CREATE DATABASE IF NOT EXISTS pictsmanager_api;

USE pictsmanager_api;

CREATE TABLE IF NOT EXISTS users
(
    id          BIGINT NOT NULL auto_increment PRIMARY KEY,
    public_id   CHAR(36) NOT NULL UNIQUE DEFAULT (UUID()),
    username    VARCHAR(50) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL UNIQUE,
    date_of_birth DATE NOT NULL,
    is_public    TINYINT(1) NOT NULL DEFAULT 0,
    is_banned    TINYINT(1) NOT NULL DEFAULT 0
)
    engine=innodb;

CREATE TABLE IF NOT EXISTS user_profile
(
    id             BIGINT NOT NULL auto_increment PRIMARY KEY,
    description    VARCHAR(100) NULL,
    profile_picture VARCHAR(512) NULL,
    cover_picture   VARCHAR(512) NULL,
    user_id        BIGINT NOT NULL UNIQUE,
    CONSTRAINT fk_userprofil_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
)
    engine=innodb;

CREATE TABLE IF NOT EXISTS media
(
    id           CHAR(36)    NOT NULL PRIMARY KEY,
    user_id    BIGINT                                NOT NULL,
    original_key TEXT        NOT NULL,
    mime_type    VARCHAR(50) NOT NULL,
    status     ENUM ('UPLOADING', 'READY', 'FAILED') NOT NULL,
    width      INT                                   NULL,
    height     INT                                   NULL,
    created_at TIMESTAMP                             NOT NULL DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_media_user_id (user_id),

    CONSTRAINT fk_media_user
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON DELETE CASCADE
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS posts
(
    id         BIGINT    NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT    NOT NULL,
    caption    TEXT      NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_posts_user_id (user_id),

    CONSTRAINT fk_posts_user
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON DELETE CASCADE
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS post_media
(
    post_id  BIGINT   NOT NULL,
    media_id CHAR(36) NOT NULL,
    position INT      NOT NULL,

    PRIMARY KEY (post_id, media_id),

    INDEX idx_post_media_media_id (media_id),

    CONSTRAINT fk_postmedia_post
        FOREIGN KEY (post_id) REFERENCES posts (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_postmedia_media
        FOREIGN KEY (media_id) REFERENCES media (id)
            ON DELETE CASCADE
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS likes
(
    user_id    BIGINT    NOT NULL,
    post_id    BIGINT    NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (user_id, post_id),

    INDEX idx_likes_post_id (post_id),

    CONSTRAINT fk_likes_user
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_likes_post
        FOREIGN KEY (post_id) REFERENCES posts (id)
            ON DELETE CASCADE
)
    ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS comments
(
    id         BIGINT    NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id    BIGINT    NOT NULL,
    post_id    BIGINT    NOT NULL,
    parent_id  BIGINT    NULL,
    content    TEXT      NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    INDEX idx_comments_user_id (user_id),
    INDEX idx_comments_post_id (post_id),
    INDEX idx_comments_parent_id (parent_id),

    CONSTRAINT fk_comments_user
        FOREIGN KEY (user_id) REFERENCES users (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_comments_post
        FOREIGN KEY (post_id) REFERENCES posts (id)
            ON DELETE CASCADE,

    CONSTRAINT fk_comments_parent
        FOREIGN KEY (parent_id) REFERENCES comments (id)
            ON DELETE SET NULL
)
    ENGINE = InnoDB;