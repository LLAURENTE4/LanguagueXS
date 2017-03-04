insert into status(id,description) values(1,'Active'); -- State person
insert into status(id,description) values(2,'Inactive'); -- State person
insert into status(id,description) values(3,'Pending'); -- State class
insert into status(id,description) values(4,'Finalized'); -- State class

insert into levels(id,description) values(1,'Basic');
insert into levels(id,description) values(2,'Intermediate');
insert into levels(id,description) values(3,'Advanced');

insert into languages(id,language_name) values(1,'English');
insert into languages(id,language_name) values(2,'French');
insert into languages(id,language_name) values(3,'Chinese');
insert into languages(id,language_name) values(4,'Japanese');
insert into languages(id,language_name) values(5,'Spanish');

insert into people(person_id,first_name,last_name,email,user,password,registration_date,
type_person,id) values (1,'Juan','Quezada','1524137@utp.edu.pe','jquezada','jquezada',
now(),1,1); -- type_person 1: teacher , 2: student
insert into people(person_id,first_name,last_name,email,user,password,registration_date,
type_person,id) values (2,'Llaurente','Llaurente','llaurente@utp.edu.pe','llaurente','llaurente',
now(),1,1); -- type_person 1: teacher , 2: student
insert into people(person_id,first_name,last_name,email,user,password,registration_date,
type_person,id) values (3,'Frank','Frank','Frank@utp.edu.pe','frank','frank',
now(),2,1); -- type_person 1: teacher , 2: student
insert into people(person_id,first_name,last_name,email,user,password,registration_date,
type_person,id) values (4,'Leonardo','Caycho','caycho@utp.edu.pe','caycho','caycho',
now(),2,1); -- type_person 1: teacher , 2: student

insert into people_languages (person_id,person_id,id,id,price)
 values(1,1,1,1,40.00);
insert into people_languages (person_id,person_id,id,id,price)
 values(2,1,1,2,40.00); 
 insert into people_languages (person_id,person_id,id,id,price)
 values(3,1,1,3,40.00);
 insert into people_languages (person_id,person_id,id,id,price)
 values(4,2,2,1,50.00); 
 insert into people_languages (person_id,person_id,id,id,price)
 values(5,2,1,1,35.00); 
 
 insert into lessons(lesson_id,person_id_student,person_id_teacher,person_id,
 start_date,end_date,qualification_student,qualification_teacher,id)
 values(1,3,1,1,now(),now(),5,5,4);
 
  insert into lessons(lesson_id,person_id_student,person_id_teacher,person_id,
 start_date,end_date,qualification_student,qualification_teacher,id)
 values(2,3,2,2,now(),now(),5,5,4);
