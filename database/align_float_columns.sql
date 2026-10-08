USE gestion_clinique;

ALTER TABLE patients
    MODIFY COLUMN temperature FLOAT NOT NULL;

ALTER TABLE consultations
    MODIFY COLUMN cost FLOAT NULL;

ALTER TABLE users
    MODIFY COLUMN tarif FLOAT NULL;
