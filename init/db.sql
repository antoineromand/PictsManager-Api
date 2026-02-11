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
    user_id      BIGINT      NOT NULL references users (id),
    original_key TEXT        NOT NULL,
    mime_type    VARCHAR(50) NOT NULL,
    width        INT         NOT NULL,
    height       INT         NOT NULL,
    created_at   TIMESTAMP   NOT NULL
)
    engine=innodb;

CREATE TABLE IF NOT EXISTS posts
(
    id         BIGINT    NOT NULL auto_increment PRIMARY KEY,
    user_id    BIGINT    NOT NULL references users (id),
    caption    TEXT      NOT NULL,
    created_at TIMESTAMP NOT NULL
)
    engine = innodb;

CREATE TABLE IF NOT EXISTS post_media
(
    post_id  BIGINT NOT NULL REFERENCES posts (id) ON DELETE CASCADE,
    media_id BIGINT NOT NULL REFERENCES media (id),
    position INT    NOT NULL,
    PRIMARY KEY (post_id, media_id)
)
    engine = innodb;

CREATE TABLE IF NOT EXISTS likes
(
    user_id    BIGINT    NOT NULL REFERENCES users (id),
    post_id    BIGINT    NOT NULL REFERENCES posts (id),
    created_at TIMESTAMP NOT NULL,

    PRIMARY KEY (user_id, post_id)
) engine = innodb;

CREATE TABLE IF NOT EXISTS comments
(
    id         BIGINT    NOT NULL auto_increment PRIMARY KEY,
    user_id    BIGINT    NOT NULL references users (id),
    post_id    BIGINT    NOT NULL REFERENCES posts (id),
    parent_id BIGINT REFERENCES comments (id),
    content    TEXT      NOT NULL,
    created_at TIMESTAMP NOT NULL
)
    engine = innodb;