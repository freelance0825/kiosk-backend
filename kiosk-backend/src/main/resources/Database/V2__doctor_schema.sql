CREATE TABLE doctor (
    id SERIAL PRIMARY KEY,
    image_base64 TEXT,
    name VARCHAR(255),
    specialization VARCHAR(255),
    status VARCHAR(50),
    review DOUBLE PRECISION,
    email VARCHAR(255),
    password VARCHAR(255),
    state VARCHAR(100),
    clinic VARCHAR(255),
    gender VARCHAR(50),
    age VARCHAR(10),
    dob VARCHAR(50)
);
