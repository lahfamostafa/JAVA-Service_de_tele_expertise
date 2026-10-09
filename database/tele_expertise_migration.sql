USE gestion_clinique;

ALTER TABLE users
    MODIFY COLUMN role ENUM('INFIRMIER', 'GENERALISTE', 'SPECIALISTE') NOT NULL,
    ADD COLUMN specialite ENUM(
        'CARDIOLOGIE',
        'PNEUMOLOGIE',
        'DERMATOLOGIE',
        'NEUROLOGIE',
        'ENDOCRINOLOGIE'
    ) NULL,
    ADD COLUMN tarif FLOAT NULL;

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
    CONSTRAINT fk_demandes_consultation
        FOREIGN KEY (consultation_id) REFERENCES consultations(id) ON DELETE CASCADE,
    CONSTRAINT fk_demandes_specialiste_user
        FOREIGN KEY (specialiste_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_users_role_specialite_tarif ON users(role, specialite, tarif);
CREATE INDEX idx_demande_expertise_specialiste_statut_priorite
    ON demande_expertise(specialiste_id, statut, priorite);
CREATE INDEX idx_demande_expertise_consultation ON demande_expertise(consultation_id);
