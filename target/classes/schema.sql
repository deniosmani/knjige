CREATE DATABASE IF NOT EXISTS knjige_db;

USE knjige_db;

CREATE TABLE IF NOT EXISTS knjige (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    razred VARCHAR(50) NOT NULL,
    predmet VARCHAR(100) NOT NULL,
    izdavac VARCHAR(100) NOT NULL,
    slika_url VARCHAR(500) NOT NULL,
    INDEX idx_razred (razred),
    INDEX idx_predmet (predmet),
    INDEX idx_izdavac (izdavac)
); 