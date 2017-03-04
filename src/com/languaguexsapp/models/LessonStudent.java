package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LessonStudent {
    private Lesson lesson;
    private Person person;
    private Date dateRegistration;
    private int studentCore;
    private int teacherCore;

    public LessonStudent(Lesson lesson, Person person, Date dateRegistration, int studentCore, int teacherCore) {
        this.lesson = lesson;
        this.person = person;
        this.dateRegistration = dateRegistration;
        this.studentCore = studentCore;
        this.teacherCore = teacherCore;
    }

    public LessonStudent() {
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public int getStudentCore() {
        return studentCore;
    }

    public void setStudentCore(int studentCore) {
        this.studentCore = studentCore;
    }

    public int getTeacherCore() {
        return teacherCore;
    }

    public void setTeacherCore(int teacherCore) {
        this.teacherCore = teacherCore;
    }

    public static LessonStudent build(ResultSet resultSet, LessonsEntity lessonsEntity,PeopleEntity peopleEntity ) {
        try {
            return new LessonStudent(
                    lessonsEntity.findById( resultSet.getInt("lesson_id")),
                    peopleEntity.findById( resultSet.getInt("person_id")),
                    resultSet.getDate("registration_date"),
                    resultSet.getInt("score_student"),
                    resultSet.getInt("score_teacher")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
