-- Add foreign key from post_consultation to appointment
ALTER TABLE post_consultation
ADD FOREIGN KEY (appointment_id)
REFERENCES appointment(id)
ON DELETE CASCADE;

-- Add foreign key from appointment to post_consultation
ALTER TABLE appointment
ADD FOREIGN KEY (consult_id)
REFERENCES post_consultation(id)
ON DELETE CASCADE;
