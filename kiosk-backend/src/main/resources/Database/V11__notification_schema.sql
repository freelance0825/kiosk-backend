CREATE TABLE notification (
    id SERIAL PRIMARY KEY,
    appointment_id BIGINT,
    is_booked BOOLEAN DEFAULT FALSE,
    is_rescheduled BOOLEAN DEFAULT FALSE,
    is_cancelled BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (appointment_id) REFERENCES appointment(id) ON DELETE SET NULL
);