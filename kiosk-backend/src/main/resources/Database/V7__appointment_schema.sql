CREATE TABLE appointment (
    id SERIAL PRIMARY KEY,
    doctor_id BIGINT NOT NULL,
    patient_id BIGINT NOT NULL,
    consult_id BIGINT,
    image_base64 TEXT,
    date_time TIMESTAMPTZ,
    name VARCHAR(255),
    specialization VARCHAR(255),
    health_complaints TEXT,

    FOREIGN KEY (doctor_id) REFERENCES doctor(id) ON DELETE CASCADE,
    FOREIGN KEY (patient_id) REFERENCES users(id) ON DELETE CASCADE
);
