create sequence hibernate_sequence
;

create table patient
(
  id bigint not null
    constraint patients_pkey
    primary key,
  firstname varchar(50) not null,
  lastname varchar(50) not null,
  created timestamp not null,
  changed timestamp not null
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
  role_id bigint not null,
  created timestamp not null,
  changed timestamp not null
)
;

comment on column staff.pwhash is 'sha256 hash'
;

create table drug
(
  id bigint not null
    constraint drugs_pkey
    primary key,
  name varchar(50) not null,
  created timestamp not null,
  changed timestamp not null
)
;

create table appointment
(
  id bigint not null
    constraint appointments_pkey
    primary key,
  date timestamp not null,
  title varchar(50) not null,
  description varchar(1000) not null,
  staff_id bigint not null
    constraint appointments_staff_id_fk
    references staff
    constraint fkl8st4gxrhtqhwcr6e5eb32ft5
    references staff,
  patient_id bigint not null
    constraint appointments_patients_id_fk
    references patient
    constraint fkfhg8dxc8emt4u70sonm2xqxnr
    references patient,
  finished timestamp,
  protocol varchar(1000),
  delay integer,
  created timestamp not null,
  changed timestamp not null,
  status integer,
  enddate timestamp not null
)
;

comment on column appointment.date is 'planned date and time for appointment'
;

comment on column appointment.finished is 'when appointment was finished'
;

comment on column appointment.delay is 'delay in minutes'
;

create table drug_task
(
  id bigint not null
    constraint appointment_drug_pkey
    primary key,
  task_id bigint not null,
  drug_id bigint not null
    constraint appointment_drug_drugs_id_fk
    references drug,
  amount integer not null,
  amount_units varchar(20),
  created timestamp not null,
  changed timestamp not null
)
;

comment on column drug_task.amount_units is 'pieces, mg, ml etc'
;

create table role
(
  id bigint not null
    constraint roles_pkey
    primary key,
  name varchar(50) not null,
  created timestamp not null,
  changed timestamp not null
)
;

alter table staff
  add constraint fkc270scukp9o70yt499swxjqah
foreign key (role_id) references role
;

alter table staff
  add constraint staff_roles_id_fk
foreign key (role_id) references role
;

create table task
(
  id bigint not null
    constraint tasks_id_pk
    primary key,
  name varchar(100) not null,
  created timestamp not null,
  changed timestamp not null,
	appointment_id bigint not null
		constraint tasks_appointment_id_fk
			references appointment,
	finished boolean not null,
	duration integer
)
;

alter table drug_task
  add constraint drugtasks_tasks_id_fk
foreign key (task_id) references task
;

create table habit
(
  id bigint not null
    constraint habits_id_pk
    primary key,
  name varchar(200) not null,
  created timestamp not null,
  changed timestamp not null
)
;

create table patient_habit
(
  id bigint not null
    constraint patienthabits_pkey
    primary key,
  patient_id bigint not null
    constraint patienthabits_patients_id_fk
    references patient,
  habit_id bigint not null
    constraint patienthabits_habits_id_fk
    references habit,
  created timestamp not null,
  changed timestamp not null
)
;

create table emergencycontact
(
  id bigint not null
    constraint emergency_contacts_pkey
    primary key,
  firstname varchar(50) not null,
  lastname varchar(50) not null,
  phonenumber varchar(20) not null,
  patient_id bigint not null
    constraint emergency_contacts_patients_id_fk
    references patient,
  created timestamp not null,
  changed timestamp not null
)
;

create table patient_drug
(
  id bigint not null
    constraint patient_drugs_pkey
    primary key,
  patient_id bigint not null
    constraint patient_drugs_patients_id_fk
    references patient,
  drug_id bigint not null
    constraint patient_drugs_drugs_id_fk
    references drug,
  created timestamp not null,
  changed timestamp not null
)
;

