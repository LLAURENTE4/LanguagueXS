DROP DATABASE IF EXISTS bd_languagexs;

CREATE DATABASE bd_languagexs;

USE bd_languagexs;


DROP TABLE IF EXISTS languages;

CREATE TABLE languages (
  id int(11) NOT NULL,
  description varchar(150) NOT NULL,
  PRIMARY KEY (id)
) ;

INSERT INTO languages VALUES (1,'English'),(2,'French'),(3,'Chinese'),(4,'Japanese'),(5,'Spanish');


DROP TABLE IF EXISTS levels;

CREATE TABLE levels (
  id int(11) NOT NULL,
  description varchar(30) NOT NULL,
  PRIMARY KEY (id)
) ;

INSERT INTO levels VALUES (1,'Basic'),(2,'Intermediate'),(3,'Advanced');


DROP TABLE IF EXISTS status;

CREATE TABLE status (
  id int(11) NOT NULL,
  description varchar(30) NOT NULL,
  PRIMARY KEY (id)
) ;

INSERT INTO status VALUES (0,'Inactivo'),(1,'Activo');


DROP TABLE IF EXISTS people;

CREATE TABLE people (
  id int(11) NOT NULL,
  first_name varchar(150) NOT NULL,
  last_name varchar(150) NOT NULL,
  email varchar(150) NOT NULL,
  password varchar(100) NOT NULL,
  registration_date date DEFAULT NULL,
  status_id int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (id),
  KEY status_people_fk (status_id),
  CONSTRAINT status_people_fk FOREIGN KEY (status_id) REFERENCES status (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

INSERT INTO people VALUES (1,'Frank','Cosme Oropeza','fcosmeo@utp.edu.pe','123456','2017-03-04',1),(2,'Luis','Laurente Areas','llaurentea@utp.edu.pe','123456','2017-03-03',1),(3,'Leonardo','Caycho','lcaycho@utp.edu.pe','123456','2017-03-06',1),(4,'Juan','Quezada','jquezada@utp.edu.pe','123456','2017-03-04',1);


DROP TABLE IF EXISTS skills;

CREATE TABLE skills (
  id int(11) NOT NULL,
  person_id int(11) NOT NULL,
  language_id int(11) NOT NULL,
  level_id int(11) NOT NULL,
  price decimal(12,2) DEFAULT NULL,
  PRIMARY KEY (id),
  KEY languages_skills_fk (language_id),
  KEY levels_skills_fk (level_id),
  KEY people_skills_fk (person_id),
  CONSTRAINT languages_skills_fk FOREIGN KEY (language_id) REFERENCES languages (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT levels_skills_fk FOREIGN KEY (level_id) REFERENCES levels (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT people_skills_fk FOREIGN KEY (person_id) REFERENCES people (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

INSERT INTO skills VALUES (1,1,1,3,15.25),(2,3,4,2,30.21);


DROP TABLE IF EXISTS lessons;

CREATE TABLE lessons (
  id int(11) NOT NULL,
  skill_id int(11) NOT NULL,
  start_date date DEFAULT NULL,
  end_date date DEFAULT NULL,
  status_id int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (id),
  KEY status_lessons_fk (status_id),
  KEY skills_lessons_fk (skill_id),
  CONSTRAINT skills_lessons_fk FOREIGN KEY (skill_id) REFERENCES skills (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT status_lessons_fk FOREIGN KEY (status_id) REFERENCES status (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

INSERT INTO lessons VALUES (1,1,'2017-03-06','2017-03-14',1),(2,1,'2017-03-15','2017-03-31',1),(3,2,'2017-03-15','2017-03-31',1),(4,2,'2017-04-10','2017-05-18',1);


DROP TABLE IF EXISTS lesson_students;

CREATE TABLE lesson_students (
  lesson_id int(11) NOT NULL,
  person_id int(11) NOT NULL,
  registration_date date DEFAULT NULL,
  score_student int(11) DEFAULT NULL,
  score_teacher int(11) DEFAULT NULL,
  PRIMARY KEY (lesson_id,person_id),
  KEY lesson_lesson_students_fk (lesson_id),
  KEY people_lesson_students_fk (person_id),
  CONSTRAINT lessons_lesson_students_fk FOREIGN KEY (lesson_id) REFERENCES lessons (id) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT people_lesson_students_fk FOREIGN KEY (person_id) REFERENCES people (id) ON DELETE NO ACTION ON UPDATE NO ACTION
) ;

INSERT INTO lesson_students VALUES (1,3,'2017-03-02',NULL,NULL),(2,4,'2017-03-02',NULL,NULL),(3,3,'2017-03-09',NULL,NULL);


DROP TABLE IF EXISTS tables;

CREATE TABLE tables(
	name varchar(150) NOT NULL,
    id int(11) NOT NULL DEFAULT '0',
    PRIMARY KEY (name)
);

INSERT INTO tables VALUES('languages',5),('lessons',4),('levels',3),('people',4),('skills',2),('status',1);


DROP PROCEDURE IF EXISTS sp_getIdTable;
DELIMITER $$
CREATE PROCEDURE sp_getIdTable(
  IN table_name varchar(150),
  OUT table_id int
)
BEGIN
	DECLARE rowcount int default 0;
    
	SELECT count(*),max(id)
    INTO rowcount,table_id
    FROM tables
    WHERE name=table_name;   
	
    if rowcount=0 or isnull(rowcount) then    
		set table_id=1;
        
		INSERT INTO tables VALUES(table_name,table_id);
	
	else
		set table_id=table_id+1;
        
		UPDATE tables
			SET id=table_id
		WHERE name=table_name;
	end if;
END$$
DELIMITER ;