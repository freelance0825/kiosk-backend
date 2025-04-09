CREATE TABLE prescription (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    morning VARCHAR(255),
    afternoon VARCHAR(255),
    night VARCHAR(255),
    notes TEXT,
    duration VARCHAR(255),
    consult_id BIGINT NOT NULL,
    image_base64 TEXT,

    FOREIGN KEY (consult_id) REFERENCES post_consultation(id) ON DELETE CASCADE
);
