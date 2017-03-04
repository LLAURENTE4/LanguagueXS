package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonStudentsEntity  extends BaseEntity{
    private LessonsEntity lessonsEntity;
    private PeopleEntity peopleEntity;


    public LessonsEntity getLessonsEntity() {
        return lessonsEntity;
    }

    public void setLessonsEntity(LessonsEntity lessonsEntity) {
        this.lessonsEntity = lessonsEntity;
    }

    public PeopleEntity getPeopleEntity() {
        return peopleEntity;
    }

    public void setPeopleEntity(PeopleEntity peopleEntity) {
        this.peopleEntity = peopleEntity;
    }

    public LessonStudentsEntity() {
        super("lesson_students");
    }

    public List<LessonStudent> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
    }

    private List<LessonStudent> findByCriteria(String sql) {
        List<LessonStudent> lessonStudents = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while(rs.next()) {
                LessonStudent lessonStudent = LessonStudent.build(rs,getLessonsEntity(),getPeopleEntity());
                lessonStudents.add(lessonStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessonStudents;
    }
    /*
    public LessonStudent findById(int idLesson,int idPerson) {
        String statement = "SELECT * FROM lesson_students WHERE lesson_id = " +String.valueOf(idLesson)+" and person_id="+String.valueOf(idPerson);
        List<LessonStudent> lessonStudents = findByCriteria(statement);
        return lessonStudents != null ? lessonStudents.get(0) : null;
    }
    */
}
