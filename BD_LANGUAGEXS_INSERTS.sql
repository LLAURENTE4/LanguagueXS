insert into status(status_id,description) values(1,'Active'); -- State person
insert into status(status_id,description) values(2,'Inactive'); -- State person
insert into status(status_id,description) values(3,'Pending'); -- State class
insert into status(status_id,description) values(4,'Finalized'); -- State class


insert into level(level_id,description) values(1,'Basic');
insert into level(level_id,description) values(2,'Intermediate');
insert into level(level_id,description) values(3,'Advanced');

insert into language(language_id,language_name) values(1,'English');
insert into language(language_id,language_name) values(2,'French');
insert into language(language_id,language_name) values(3,'Chinese');
insert into language(language_id,language_name) values(4,'Japanese');
insert into language(language_id,language_name) values(5,'Spanish');

insert into person(person_id,first_name,last_name,email,user,password,registration_date,
type_person,status_id) values (1,'Juan','Quezada','1524137@utp.edu.pe','jquezada','jquezada',
now(),1,1); -- type_person 1: teacher , 2: student
insert into person(person_id,first_name,last_name,email,user,password,registration_date,
type_person,status_id) values (2,'Llaurente','Llaurente','llaurente@utp.edu.pe','llaurente','llaurente',
now(),1,1); -- type_person 1: teacher , 2: student
insert into person(person_id,first_name,last_name,email,user,password,registration_date,
type_person,status_id) values (3,'Frank','Frank','Frank@utp.edu.pe','frank','frank',
now(),2,1); -- type_person 1: teacher , 2: student
insert into person(person_id,first_name,last_name,email,user,password,registration_date,
type_person,status_id) values (4,'Leonardo','Caycho','caycho@utp.edu.pe','caycho','caycho',
now(),2,1); -- type_person 1: teacher , 2: student

insert into person_language (person_language_id,person_id,language_id,level_id,price)
 values(1,1,1,1,40.00);
insert into person_language (person_language_id,person_id,language_id,level_id,price)
 values(2,1,1,2,40.00); 
 insert into person_language (person_language_id,person_id,language_id,level_id,price)
 values(3,1,1,3,40.00);
 insert into person_language (person_language_id,person_id,language_id,level_id,price)
 values(4,2,2,1,50.00); 
 insert into person_language (person_language_id,person_id,language_id,level_id,price)
 values(5,2,1,1,35.00); 
 
 insert into lesson(lesson_id,person_id_student,person_id_teacher,person_language_id,
 start_date,end_date,qualification_student,qualification_teacher,status_id)
 values(1,3,1,1,now(),now(),5,5,4);
 
  insert into lesson(lesson_id,person_id_student,person_id_teacher,person_language_id,
 start_date,end_date,qualification_student,qualification_teacher,status_id)
 values(2,3,2,2,now(),now(),5,5,4);
