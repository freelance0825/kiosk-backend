CREATE TABLE notification (
    id SERIAL PRIMARY KEY,
    appointment_id BIGINT,
    appt_users_id BIGINT,
    appt_doctor_id BIGINT,
    is_booked BOOLEAN DEFAULT FALSE,
    is_rescheduled BOOLEAN DEFAULT FALSE,
    is_cancelled BOOLEAN DEFAULT FALSE,
    appt_date_time TIMESTAMP,
    appt_users_name VARCHAR(255),
    appt_doctor_name VARCHAR(255),
    appt_doctor_specialization VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    FOREIGN KEY (appointment_id) REFERENCES appointment(id) ON DELETE SET NULL,
    FOREIGN KEY (appt_users_id) REFERENCES users(id) ON DELETE SET NULL,
    FOREIGN KEY (appt_doctor_id) REFERENCES doctor(id) ON DELETE SET NULL
);