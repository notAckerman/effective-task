CREATE TABLE comments
(
    author_id  BIGINT,
    created_at DATETIME(6),
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    task_id    BIGINT,
    updated_at DATETIME(6),
    text       VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE priorities
(
    created_at DATETIME(6),
    id         BIGINT NOT NULL AUTO_INCREMENT,
    updated_at DATETIME(6),
    priority   ENUM ('HIGH', 'LOW', 'MEDIUM'),
    PRIMARY KEY (id)
);

CREATE TABLE roles
(
    created_at DATETIME(6),
    id         BIGINT NOT NULL AUTO_INCREMENT,
    updated_at DATETIME(6),
    role       ENUM ('ROLE_ADMIN', 'ROLE_USER'),
    PRIMARY KEY (id)
);

CREATE TABLE statuses
(
    created_at DATETIME(6),
    id         BIGINT NOT NULL AUTO_INCREMENT,
    updated_at DATETIME(6),
    status     ENUM ('COMPLETED', 'IN_PROGRESS', 'PENDING'),
    PRIMARY KEY (id)
);

CREATE TABLE tasks
(
    assignee_id BIGINT,
    author_id   BIGINT,
    created_at  DATETIME(6),
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    priority_id BIGINT,
    status_id   BIGINT,
    updated_at  DATETIME(6),
    description VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    created_at DATETIME(6),
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    updated_at DATETIME(6),
    email      VARCHAR(255) NOT NULL,
    name       VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users_authorities
(
    authorities_id BIGINT NOT NULL,
    user_id        BIGINT NOT NULL
);


INSERT INTO priorities (priority)
VALUES ('HIGH');
INSERT INTO priorities (priority)
VALUES ('MEDIUM');
INSERT INTO priorities (priority)
VALUES ('LOW');

INSERT INTO statuses (status)
VALUES ('PENDING');
INSERT INTO statuses (status)
VALUES ('IN_PROGRESS');
INSERT INTO statuses (status)
VALUES ('COMPLETED');

INSERT INTO roles (role)
VALUES ('ROLE_USER');
INSERT INTO roles (role)
VALUES ('ROLE_ADMIN');

INSERT INTO users (created_at, updated_at, email, name, password)
VALUES
    (NOW(), NOW(), 'admin@example.com', 'Admin', '$2a$10$YdbFOHE46XgXYvtnNI9O0ugqdXSX4oHUXiJP1KvXMlUBgaJ56S3MG'),
    (NOW(), NOW(), 'user@example.com', 'User', '$2a$10$YdbFOHE46XgXYvtnNI9O0ugqdXSX4oHUXiJP1KvXMlUBgaJ56S3MG');

SET @admin_role_id = (SELECT id FROM roles WHERE role = 'ROLE_ADMIN');
SET @user_role_id = (SELECT id FROM roles WHERE role = 'ROLE_USER');

SET @admin_user_id = (SELECT id FROM users WHERE email = 'admin@example.com');
SET @user_user_id = (SELECT id FROM users WHERE email = 'user@example.com');

INSERT INTO users_authorities (authorities_id, user_id)
VALUES
    (@admin_role_id, @admin_user_id),
    (@user_role_id, @user_user_id);