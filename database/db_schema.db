CREATE DATABASE IF NOT EXISTS gestion_clinique
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE gestion_clinique;
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role ENUM('INFIRMIER', 'GENERALISTE', 'SPECIALISTE') NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE patients (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    last_name VARCHAR(100) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    social_security_number VARCHAR(50) NOT NULL UNIQUE,
    blood_pressure VARCHAR(30) NOT NULL,
    heart_rate INT NOT NULL,
    temperature FLOAT NOT NULL,
    respiratory_rate INT NOT NULL,
    arrived_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_patients_heart_rate_positive CHECK (heart_rate > 0),
    CONSTRAINT chk_patients_temperature_positive CHECK (temperature > 0),
    CONSTRAINT chk_patients_respiratory_rate_positive CHECK (respiratory_rate > 0)
);
CREATE TABLE consultations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    patient_id BIGINT NOT NULL,
    doctor_id BIGINT NULL,
    reason VARCHAR(255) NULL,
    observations TEXT NULL,
    diagnosis TEXT NULL,
    prescribed_treatment TEXT NULL,
    cost FLOAT NULL,
    status ENUM('EN_ATTENTE', 'TERMINEE') NOT NULL DEFAULT 'EN_ATTENTE',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    closed_at DATETIME NULL,
    CONSTRAINT fk_consultations_patient FOREIGN KEY (patient_id) REFERENCES patients(id),
    CONSTRAINT fk_consultations_doctor FOREIGN KEY (doctor_id) REFERENCES users(id),
    CONSTRAINT chk_consultations_cost_positive CHECK (cost >= 0)
);
-- Accélère les listes de patients arrivés à une date donnée.
CREATE INDEX idx_patients_arrived_at ON patients(arrived_at);
-- Accélère la recherche des consultations d'un patient.
CREATE INDEX idx_consultations_patient_id ON consultations(patient_id);
CREATE INDEX idx_consultations_status_created_at ON consultations(status, created_at);

-------------
-- seeders --
INSERT INTO users (username, password_hash, role)
VALUES
    ('infirmier1', '$2a$10$lDaQDfVgXdX6rgEFvsyDyOKzwzU0aTdkDMdbI8MGvbYsttyIHva.6', 'INFIRMIER'),
    ('generaliste1', '$2a$10$cc.3KTHg.WmZWLL2kYGfwOo82TGixnzTJxbkrJ0W5hnEItldk.fnK', 'GENERALISTE');


-- modification de la table users pour ajouter specialité --
ALTER TABLE users
     ADD COLUMN specialite ENUM('CARDIOLOGIE', 'PNEUMOLOGIE', 'DERMATOLOGIE', 'NEUROLOGIE', 'ENDOCRINOLOGIE') NULL,
     ADD COLUMN tarif FLOAT NULL;

-- ajouter table demande_expertise

CREATE TABLE demande_expertise (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    consultation_id BIGINT NOT NULL,
    specialiste_id BIGINT NOT NULL, 
    question TEXT NOT NULL,
    priorite ENUM('URGENTE', 'NORMALE', 'NON_URGENTE') NOT NULL DEFAULT 'NORMALE',
    statut ENUM('EN_ATTENTE', 'TERMINEE') NOT NULL DEFAULT 'EN_ATTENTE',
    avis TEXT NULL,
    recommandations TEXT NULL,
    date_creation DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_demandes_consultation FOREIGN KEY (consultation_id) REFERENCES consultations(id) ON DELETE CASCADE,
    CONSTRAINT fk_demandes_specialiste_user FOREIGN KEY (specialiste_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_users_role_specialite_tarif ON users(role, specialite, tarif);
CREATE INDEX idx_demande_expertise_specialiste_statut_priorite
    ON demande_expertise(specialiste_id, statut, priorite);
CREATE INDEX idx_demande_expertise_consultation ON demande_expertise(consultation_id);
