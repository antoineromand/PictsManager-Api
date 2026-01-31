CREATE DATABASE IF NOT EXISTS pictsmanager_api;

USE pictsmanager_api;

CREATE TABLE IF NOT EXISTS users
(
    id          BIGINT NOT NULL auto_increment PRIMARY KEY,
    username    VARCHAR(50) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL UNIQUE,
    dateofbirth DATE NOT NULL,
    ispublic    TINYINT(1) NOT NULL DEFAULT 0,
    isbanned    TINYINT(1) NOT NULL DEFAULT 0
)
    engine=innodb;

CREATE TABLE IF NOT EXISTS user_profil
(
    id             BIGINT NOT NULL auto_increment PRIMARY KEY,
    description    VARCHAR(100) NULL,
    profilepicture VARCHAR(512) NULL,
    coverpicture   VARCHAR(512) NULL,
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
    date        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_id     BIGINT NOT NULL,
    INDEX idx_images_user_id (user_id),
    CONSTRAINT fk_images_user FOREIGN KEY (user_id) REFERENCES users(id) ON
        DELETE CASCADE
)
    engine=innodb;