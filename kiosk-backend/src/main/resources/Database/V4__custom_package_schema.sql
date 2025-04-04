CREATE TABLE custom_package (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    icon TEXT,
    tests TEXT,
    consult_id BIGINT NOT NULL,
    FOREIGN KEY (consult_id) REFERENCES users(id) ON DELETE CASCADE
);
