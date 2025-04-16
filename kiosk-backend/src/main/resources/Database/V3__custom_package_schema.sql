CREATE TABLE custom_package (
    id SERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    name VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (patient_id) REFERENCES users(id) ON DELETE CASCADE
);
