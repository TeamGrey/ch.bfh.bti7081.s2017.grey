CREATE TABLE appointment
(
  id BIGINT PRIMARY KEY NOT NULL,
  date TIMESTAMP NOT NULL,
  title VARCHAR(50) NOT NULL,
  description VARCHAR(1000) NOT NULL,
  staff_id BIGINT NOT NULL,
  patient_id BIGINT NOT NULL,
  finished TIMESTAMP,
  protocol VARCHAR(1000),
  delay INTEGER,
  created TIMESTAMP NOT NULL,
  changed TIMESTAMP NOT NULL,
  status_id INTEGER,
  status INTEGER,
  enddate TIMESTAMP NOT NULL,
  CONSTRAINT appointments_staff_id_fk FOREIGN KEY (staff_id) REFERENCES staff (id),
  CONSTRAINT fkl8st4gxrhtqhwcr6e5eb32ft5 FOREIGN KEY (staff_id) REFERENCES staff (id),
  CONSTRAINT appointments_patients_id_fk FOREIGN KEY (patient_id) REFERENCES patient (id),
  CONSTRAINT fkfhg8dxc8emt4u70sonm2xqxnr FOREIGN KEY (patient_id) REFERENCES patient (id)
);
COMMENT ON COLUMN appointment.date IS 'planned date and time for appointment';
COMMENT ON COLUMN appointment.finished IS 'when appointment was finished';
COMMENT ON COLUMN appointment.delay IS 'delay in minutes';
CREATE TABLE drug
(
  id BIGINT PRIMARY KEY NOT NULL,
  name VARCHAR(50) NOT NULL,
  created TIMESTAMP NOT NULL,
  changed TIMESTAMP NOT NULL
);
CREATE TABLE drug_task
(
  id BIGINT PRIMARY KEY NOT NULL,
  task_id BIGINT NOT NULL,
  drug_id BIGINT NOT NULL,
  amount INTEGER NOT NULL,
  amount_units VARCHAR(20),
  created TIMESTAMP NOT NULL,
  changed TIMESTAMP NOT NULL,
  CONSTRAINT drugtasks_tasks_id_fk FOREIGN KEY (task_id) REFERENCES task (id),
  CONSTRAINT appointment_drug_drugs_id_fk FOREIGN KEY (drug_id) REFERENCES drug (id)
);
COMMENT ON COLUMN drug_task.amount_units IS 'pieces, mg, ml etc';
CREATE TABLE emergencycontact
(
  id BIGINT PRIMARY KEY NOT NULL,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  phonenumber VARCHAR(20) NOT NULL,
  patient_id BIGINT NOT NULL,
  created TIMESTAMP NOT NULL,
  changed TIMESTAMP NOT NULL,
  CONSTRAINT emergency_contacts_patients_id_fk FOREIGN KEY (patient_id) REFERENCES patient (id)
);
CREATE TABLE habit
(
  id BIGINT PRIMARY KEY NOT NULL,
  name VARCHAR(200) NOT NULL,
  created TIMESTAMP NOT NULL,
  changed TIMESTAMP NOT NULL
);
CREATE TABLE patient
(
  id BIGINT PRIMARY KEY NOT NULL,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  created TIMESTAMP NOT NULL,
  changed TIMESTAMP NOT NULL
);
CREATE TABLE patient_drug
(
  id BIGINT PRIMARY KEY NOT NULL,
  patient_id BIGINT NOT NULL,
  drug_id BIGINT NOT NULL,
  created TIMESTAMP NOT NULL,
  changed TIMESTAMP NOT NULL,
  CONSTRAINT patient_drugs_patients_id_fk FOREIGN KEY (patient_id) REFERENCES patient (id),
  CONSTRAINT patient_drugs_drugs_id_fk FOREIGN KEY (drug_id) REFERENCES drug (id)
);
CREATE TABLE patient_habit
(
  id BIGINT PRIMARY KEY NOT NULL,
  patient_id BIGINT NOT NULL,
  habit_id BIGINT NOT NULL,
  created TIMESTAMP NOT NULL,
  changed TIMESTAMP NOT NULL,
  CONSTRAINT patienthabits_patients_id_fk FOREIGN KEY (patient_id) REFERENCES patient (id),
  CONSTRAINT patienthabits_habits_id_fk FOREIGN KEY (habit_id) REFERENCES habit (id)
);
CREATE TABLE role
(
  id BIGINT PRIMARY KEY NOT NULL,
  name VARCHAR(50) NOT NULL,
  created TIMESTAMP NOT NULL,
  changed TIMESTAMP NOT NULL
);
CREATE TABLE staff
(
  id BIGINT PRIMARY KEY NOT NULL,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  pwhash VARCHAR(64) NOT NULL,
  login VARCHAR(20) NOT NULL,
  role_id BIGINT NOT NULL,
  created TIMESTAMP NOT NULL,
  changed TIMESTAMP NOT NULL,
  CONSTRAINT fkc270scukp9o70yt499swxjqah FOREIGN KEY (role_id) REFERENCES role (id),
  CONSTRAINT staff_roles_id_fk FOREIGN KEY (role_id) REFERENCES role (id)
);
COMMENT ON COLUMN staff.pwhash IS 'sha256 hash';
CREATE TABLE task
(
  id BIGINT PRIMARY KEY NOT NULL,
  name VARCHAR(100) NOT NULL,
  created TIMESTAMP NOT NULL,
  changed TIMESTAMP NOT NULL
);