CREATE DATABASE Bd_LanguageXS;
USE Bd_LanguageXS;

CREATE TABLE languages
(
	id          integer NOT NULL,
	language_name        varchar(50) NULL
);



ALTER TABLE languages
ADD PRIMARY KEY (id);



CREATE TABLE lessons
(
	id                   integer NOT NULL,
	student_id           INTEGER NULL,
	start_date           datetime NULL,
	end_date             datetime NULL,
	qualification_student integer NULL,
	qualification_teacher integer NULL,
	status_id            INTEGER NULL,
	skills_id            INTEGER NULL
);



ALTER TABLE lessons
ADD PRIMARY KEY (id);



CREATE TABLE levels
(
	id             integer NOT NULL,
	description          varchar(30) NULL
);



ALTER TABLE levels
ADD PRIMARY KEY (id);



CREATE TABLE people
(
	id                   INTEGER NOT NULL,
	first_name           varchar(100) NULL,
	last_name            varchar(100) NULL,
	email                varchar(100) NULL,
	user                 varchar(20) NULL,
	password             varchar(20) NULL,
	registration_date    datetime NULL,
	type_person          integer NULL,
	status_id            INTEGER NULL
);



ALTER TABLE people
ADD PRIMARY KEY (id);



CREATE TABLE skills
(
	id          integer NOT NULL,
	teacher_id           INTEGER NOT NULL,
	skills_id            INTEGER NOT NULL,
	price                decimal(18,2) NULL,
	level_id             integer NULL
);



ALTER TABLE skills
ADD PRIMARY KEY (id);



CREATE TABLE status
(
	id            INTEGER NOT NULL,
	description          varchar(30) NULL
);



ALTER TABLE status
ADD PRIMARY KEY (id);



ALTER TABLE lessons
ADD FOREIGN KEY R_14 (student_id) REFERENCES people (id);



ALTER TABLE lessons
ADD FOREIGN KEY R_17 (status_id) REFERENCES status (id);



ALTER TABLE lessons
ADD FOREIGN KEY R_18 (skills_id) REFERENCES skills (id);



ALTER TABLE people
ADD FOREIGN KEY R_12 (status_id) REFERENCES status (id);



ALTER TABLE skills
ADD FOREIGN KEY R_3 (skills_id) REFERENCES languages (id);



ALTER TABLE skills
ADD FOREIGN KEY R_5 (teacher_id) REFERENCES people (id);



ALTER TABLE skills
ADD FOREIGN KEY R_15 (level_id) REFERENCES levels (id);


