CREATE TABLE custom_package_history (
    id SERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    name VARCHAR(255),
    is_general_test BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (patient_id) REFERENCES users(id) ON DELETE CASCADE
);
