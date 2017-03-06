package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonStudentsEntity  extends BaseEntity{
    private GeneralEntity generalEntity;
    private LessonsEntity lessonsEntity;
    private PeopleEntity peopleEntity;


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

    public LessonStudent findById(int idLesson,int idPerson) {
        String statement = "SELECT * FROM lesson_students WHERE lesson_id = " +String.valueOf(idLesson)+" and person_id="+String.valueOf(idPerson);
        List<LessonStudent> lessonStudents = findByCriteria(statement);
        return lessonStudents != null ? lessonStudents.get(0) : null;
    }

    private int updateByCriteria(String sql) {
        try {
            return getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public LessonStudent create(int lessonId,int personId, int studentCore, int teacherCore  ){
        String sql = "INSERT INTO lesson_students(lesson_id,person_id, registration_date) " +
                "VALUES(" + String.valueOf(lessonId) + "," + String.valueOf( personId) + ","+ String.valueOf( getGeneralEntity().getDateCurrent()) +")";
        return updateByCriteria(sql) > 0 ? new LessonStudent(getLessonsEntity().findById(lessonId),getPeopleEntity().findById(personId),getGeneralEntity().getDateCurrent(),studentCore,teacherCore) : null;
    }

    public boolean update(LessonStudent lessonStudent) {
        String sql = "UPDATE lesson_students SET score_student = " + String.valueOf(lessonStudent.getStudentCore()) + ",score_teacher= " + lessonStudent.getTeacherCore() +
                " WHERE lesson_id = " + String.valueOf(lessonStudent.getLesson().getId())+" and person_id="+String.valueOf(lessonStudent.getPerson().getId());
        return updateByCriteria(sql) > 0;
    }

    public boolean delete(int lessonId,int personId) {
        String sql = "DELETE FROM lesson_students WHERE lesson_id = " + String.valueOf(lessonId)+" and person_id="+String.valueOf(personId) ;
        return updateByCriteria(sql) > 0;
    }

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

    public GeneralEntity getGeneralEntity() {
        return generalEntity;
    }

    public void setGeneralEntity(GeneralEntity generalEntity) {
        this.generalEntity = generalEntity;
    }
}
