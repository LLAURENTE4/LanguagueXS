CREATE DATABASE Bd_LanguageXS;
USE Bd_LanguageXS;


CREATE TABLE languages
(
	language_id          integer NOT NULL,
	language_name        varchar(50) NULL
);


ALTER TABLE languages
ADD PRIMARY KEY (language_id);


CREATE TABLE lessons
(
	lesson_id            integer NOT NULL,
	person_id_student    INTEGER NULL,
	person_id_teacher    INTEGER NULL,
	person_language_id   INTEGER NULL,
	start_date           datetime NULL,
	end_date             datetime NULL,
	qualification_student integer NULL,
	qualification_teacher integer NULL,
	status_id            INTEGER NULL
);



ALTER TABLE lessons
ADD PRIMARY KEY (lesson_id);



CREATE TABLE levels
(
	level_id             integer NOT NULL,
	description          varchar(30) NULL
);



ALTER TABLE levels
ADD PRIMARY KEY (level_id);



CREATE TABLE people
(
	person_id            INTEGER NOT NULL,
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
ADD PRIMARY KEY (person_id);



CREATE TABLE people_languages
(
	person_language_id   INTEGER NOT NULL,
	person_id            INTEGER NOT NULL,
	language_id          integer NOT NULL,
	level_id             integer NULL,
	price                decimal(18,2) NULL
);



ALTER TABLE people_languages
ADD PRIMARY KEY (person_language_id);



CREATE TABLE status
(
	status_id            INTEGER NOT NULL,
	description          varchar(30) NULL
);



ALTER TABLE status
ADD PRIMARY KEY (status_id);



ALTER TABLE lessons
ADD FOREIGN KEY R_13 (person_id_student) REFERENCES people (person_id);



ALTER TABLE lessons
ADD FOREIGN KEY R_14 (person_id_teacher) REFERENCES people (person_id);



ALTER TABLE lessons
ADD FOREIGN KEY R_17 (status_id) REFERENCES Status (status_id);



ALTER TABLE lessons
ADD FOREIGN KEY R_18 (person_language_id) REFERENCES people_Languages (person_language_id);



ALTER TABLE people
ADD FOREIGN KEY R_12 (status_id) REFERENCES Status (status_id);



ALTER TABLE people_languages
ADD FOREIGN KEY R_3 (language_id) REFERENCES Languages (language_id);



ALTER TABLE people_languages
ADD FOREIGN KEY R_5 (person_id) REFERENCES people (person_id);



ALTER TABLE people_languages
ADD FOREIGN KEY R_15 (level_id) REFERENCES Levels (level_id);


