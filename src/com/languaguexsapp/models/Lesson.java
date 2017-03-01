package com.languaguexsapp.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Frank on 25/02/2017.
 */
public class Lesson implements Serializable{
    private int id;
    private String name;
    private Person personStudent;
    private Person personTeacher;
    private Date dateStart;
    private Date dateEnd;
    private int studentQualification;
    private int teacherQualification;
    private Status status;
    private Skill skill;

    public Lesson(int id, String name, Person personStudent, Person personTeacher, Date dateStart, Date dateEnd, int studentQualification, int teacherQualification, Status status, Skill skill) {
        this.setId(id);
        this.setName(name);
        this.setPersonStudent(personStudent);
        this.setPersonTeacher(personTeacher);
        this.setDateStart(dateStart);
        this.setDateEnd(dateEnd);
        this.setStudentQualification(studentQualification);
        this.setTeacherQualification(teacherQualification);
        this.setStatus(status);
        this.setSkill(skill);
    }
    public Lesson() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPersonStudent() {
        return personStudent;
    }

    public void setPersonStudent(Person personStudent) {
        this.personStudent = personStudent;
    }

    public Person getPersonTeacher() {
        return personTeacher;
    }

    public void setPersonTeacher(Person personTeacher) {
        this.personTeacher = personTeacher;
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

    public void setStatus(Status status) {
        this.status = status;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

}
