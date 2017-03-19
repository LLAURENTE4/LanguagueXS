package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LessonStudent {
    private Lesson lesson;
    private Person person;
    private Date registrationDate;
    private int scoreStudent;
    private int scoreTeacher;

    public LessonStudent(Lesson lesson, Person person, Date registrationDate, int scoreStudent, int scoreTeacher) {
        this.lesson = lesson;
        this.person = person;
        this.registrationDate = registrationDate;
        this.scoreStudent = scoreStudent;
        this.scoreTeacher = scoreTeacher;
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

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getScoreStudent() {
        return scoreStudent;
    }

    public void setScoreStudent(int scoreStudent) {
        this.scoreStudent = scoreStudent;
    }

    public int getScoreTeacher() {
        return scoreTeacher;
    }

    public void setScoreTeacher(int scoreTeacher) {
        this.scoreTeacher = scoreTeacher;
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
