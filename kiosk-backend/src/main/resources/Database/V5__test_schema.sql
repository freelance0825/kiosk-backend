CREATE TABLE test (
    id SERIAL PRIMARY KEY,
    patient_id BIGINT NOT NULL,
    custom_package_id BIGINT,
    medical_package_id BIGINT,
    name VARCHAR(255),
    result TEXT,
    range VARCHAR(255),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (patient_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (custom_package_id) REFERENCES custom_package(id) ON DELETE CASCADE,
    FOREIGN KEY (medical_package_id) REFERENCES medical_package(id) ON DELETE CASCADE
);
