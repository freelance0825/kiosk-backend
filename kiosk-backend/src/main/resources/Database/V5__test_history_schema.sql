CREATE TABLE test_history (
    id SERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    custom_package_history_id BIGINT,
    medical_package_history_id BIGINT,
    name VARCHAR(255),
    result TEXT,
    range VARCHAR(255),
     is_general_test BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (patient_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (custom_package_history_id) REFERENCES custom_package_history(id) ON DELETE CASCADE,
    FOREIGN KEY (medical_package_history_id) REFERENCES medical_package_history(id) ON DELETE CASCADE
);
