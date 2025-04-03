CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    age VARCHAR(10),
    gender VARCHAR(10),
    address TEXT,
    date_of_birth DATE,
    phone_number VARCHAR(15) NOT NULL,
    image_base64 TEXT
);
