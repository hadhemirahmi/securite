CREATE DATABASE IF NOT EXISTS jee_login CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE jee_login;

CREATE TABLE IF NOT EXISTS users (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    login      VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(64)  NOT NULL,          -- hash SHA-256 (64 caracteres hex)
    created_at TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);
