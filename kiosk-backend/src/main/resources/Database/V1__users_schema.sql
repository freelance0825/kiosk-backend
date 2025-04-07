CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    age VARCHAR(10),
    gender VARCHAR(10),
    email VARCHAR(100),
    address TEXT,
    date_of_birth VARCHAR(50),
    phone_number VARCHAR(15) ,
    image_base64 TEXT
);
