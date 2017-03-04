package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LessonsEntity extends BaseEntity{
    private SkillsEntity skillsEntity;
    private StatusEntity statusEntity;

    public LessonsEntity() {
        super("lessons");
    }

    public List<Lesson> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
    }

    private List<Lesson> findByCriteria(String sql) {
        List<Lesson> lessons = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while(rs.next()) {
                Lesson lesson = Lesson.build(rs,getSkillsEntity(),getStatusEntity());
                lessons.add(lesson);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public Lesson findById(int id) {
        String statement = "SELECT * FROM lessons WHERE id = " +String.valueOf(id);
        List<Lesson> lesson = findByCriteria(statement);
        return lesson != null ? lesson.get(0) : null;
    }

    public SkillsEntity getSkillsEntity() {
        return skillsEntity;
    }

    public void setSkillsEntity(SkillsEntity skillsEntity) {
        this.skillsEntity = skillsEntity;
    }

    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
    }
}
