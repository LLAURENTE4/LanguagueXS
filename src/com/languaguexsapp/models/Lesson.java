package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Frank on 25/02/2017.
 */
public class Lesson {
    private int id;
    // private String name;
    private People personStudent;
    // private People personTeacher;
    //    private  PeopleLanguagesEntity peopleLanguagesEntity;
    private  PersonLanguage personLanguage;
    private Date dateStart;


    private Date dateEnd;
    private int studentQualification;
    private int teacherQualification;
    private Status status;


    public Lesson(int id, People personStudent, PersonLanguage personLanguage, Date dateStart, Date dateEnd, int studentQualification, int teacherQualification, Status status) {
        this.id = id;
        //   this.name = name;
        this.personStudent = personStudent;
        //this.personTeacher = personTeacher;
        this.personLanguage = personLanguage;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.studentQualification = studentQualification;
        this.teacherQualification = teacherQualification;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public People getPersonStudent() {
        return personStudent;
    }

    public void setPersonStudent(People personStudent) {
        this.personStudent = personStudent;
    }
/*
    public Person getPersonTeacher() {
        return personTeacher;
    }

    public void setPersonTeacher(Person personTeacher) {
        this.personTeacher = personTeacher;
    }
*/
    public PersonLanguage getPersonLanguage() {
        return personLanguage;
    }

    public void setPersonLanguage(PersonLanguage personLanguage) {
        this.personLanguage = personLanguage;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getStudentQualification() {
        return studentQualification;
    }

    public void setStudentQualification(int studentQualification) {
        this.studentQualification = studentQualification;
    }

    public int getTeacherQualification() {
        return teacherQualification;
    }

    public void setTeacherQualification(int teacherQualification) {
        this.teacherQualification = teacherQualification;
    }

    public Status getStatus() {
        return status;
    }

    public void setState(Status status) {
        this.status = status;
    }

    public static Lesson build(ResultSet resultSet,PeopleEntity peopleEntity, PeopleLanguagesEntity peopleLanguagesEntity,StatusEntity status ) {
        try {
            return new Lesson(resultSet.getInt("lesson_id"),
                    peopleEntity.findById(   resultSet.getInt("person_id_student")),
               //     peopleEntity.findById(    resultSet.getInt("person_id_teacher")),
                    peopleLanguagesEntity.findById( resultSet.getInt("person_language_id")),
                    resultSet.getDate("start_date"),
                    resultSet.getDate("end_date"),
                    resultSet.getInt("qualification_student"),
                    resultSet.getInt("qualification_teacher"),
                    status.findById( resultSet.getInt("status_id") ));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


}
