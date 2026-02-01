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
    CONSTRAINT fk_userprofil_user FOREIGN KEY (user_id) REFERENCES users(id) ON
        DELETE CASCADE
)
    engine=innodb;

CREATE TABLE IF NOT EXISTS images
(
    id          BIGINT NOT NULL auto_increment PRIMARY KEY,
    description VARCHAR(100) NULL,
    name        VARCHAR(255) NULL,
    path        VARCHAR(512) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id     BIGINT NOT NULL,
    INDEX idx_images_user_id (user_id),
    CONSTRAINT fk_images_user FOREIGN KEY (user_id) REFERENCES users(id) ON
        DELETE CASCADE
)
    engine=innodb;