create table patients
(
  id bigint not null
    constraint patients_pkey
    primary key,
  firstname varchar(50) not null,
  lastname varchar(50) not null
)
;

create table staff
(
  id bigint not null
    constraint staff_pkey
    primary key,
  firstname varchar(50) not null,
  lastname varchar(50) not null,
  pwhash varchar(64) not null,
  login varchar(20) not null,
  role_id bigint not null
)
;

create table drugs
(
  id bigint not null
    constraint drugs_pkey
    primary key,
  name varchar(50) not null
)
;

create table appointments
(
  id bigint not null
    constraint appointments_pkey
    primary key,
  date timestamp not null,
  title varchar(50) not null,
  description varchar(1000) not null,
  staff_id bigint not null
    constraint appointments_staff_id_fk
    references staff,
  patient_id bigint not null
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
  task_id bigint not null,
  drug_id bigint not null
    constraint appointment_drug_drugs_id_fk
    references drugs,
  amount integer not null
)
;

create table roles
(
  id bigint not null
    constraint roles_pkey
    primary key,
  name varchar(50) not null
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
  name varchar(100) not null
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
  name varchar(200) not null
)
;

create table patienthabits
(
  id bigint not null
    constraint patienthabits_pkey
    primary key,
  patient_id bigint not null
    constraint patienthabits_patients_id_fk
    references patients,
  habit_id bigint not null
    constraint patienthabits_habits_id_fk
    references habits
)
;

create table emergency_contacts
(
  id bigint not null
    constraint emergency_contacts_pkey
    primary key,
  firstname varchar(50) not null,
  lastname varchar(50) not null,
  phonenumber varchar(20) not null,
  patient_id bigint not null
    constraint emergency_contacts_patients_id_fk
    references patients
)
;

create table patient_drugs
(
  id bigint not null
    constraint patient_drugs_pkey
    primary key,
  patient_id bigint not null
    constraint patient_drugs_patients_id_fk
    references patients,
  drug_id bigint not null
    constraint patient_drugs_drugs_id_fk
    references drugs
)
;

