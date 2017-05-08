create table patients
(
  id bigint not null
    constraint patients_pkey
    primary key,
  firstname varchar(50),
  lastname varchar(50)
)
;

create table staff
(
  id bigint not null
    constraint staff_pkey
    primary key,
  firstname varchar(50),
  lastname varchar(50),
  pwhash varchar(64),
  login varchar(20),
  role_id bigint
)
;

create table drugs
(
  id bigint not null
    constraint drugs_pkey
    primary key,
  name varchar(50)
)
;

create table appointments
(
  id bigint not null
    constraint appointments_pkey
    primary key,
  date timestamp,
  title varchar(50),
  description varchar(1000),
  staff_id bigint
    constraint appointments_staff_id_fk
    references staff,
  patient_id bigint
    constraint appointments_patients_id_fk
    references patients,
  finished timestamp,
  protocol varchar(1000),
  delay integer
)
;

create table drugtasks
(
  id bigint not null
    constraint appointment_drug_pkey
    primary key,
  task_id bigint,
  drug_id bigint
    constraint appointment_drug_drugs_id_fk
    references drugs,
  amount integer
)
;

create table roles
(
  id bigint not null
    constraint roles_pkey
    primary key,
  name integer
)
;

alter table staff
  add constraint staff_roles_id_fk
foreign key (role_id) references roles
;

create table tasks
(
  id bigint not null
    constraint tasks_id_pk
    primary key,
  name integer
)
;

alter table drugtasks
  add constraint drugtasks_tasks_id_fk
foreign key (task_id) references tasks
;

create table habits
(
  id bigint not null
    constraint habits_id_pk
    primary key,
  name varchar(200)
)
;

create table patienthabits
(
  id bigint not null
    constraint patienthabits_pkey
    primary key,
  patient_id bigint
    constraint patienthabits_patients_id_fk
    references patients,
  habit_id bigint
    constraint patienthabits_habits_id_fk
    references habits
)
;

create table emergency_contacts
(
  id bigint not null
    constraint emergency_contacts_pkey
    primary key,
  firstname varchar(50),
  lastname varchar(50),
  phonenumber varchar(20),
  patient_id bigint
    constraint emergency_contacts_patients_id_fk
    references patients
)
;

create table patient_drugs
(
  id bigint not null
    constraint patient_drugs_pkey
    primary key,
  patient_id bigint
    constraint patient_drugs_patients_id_fk
    references patients,
  drug_id bigint
    constraint patient_drugs_drugs_id_fk
    references drugs
)
;

