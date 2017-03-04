package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
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

    public Lesson findByName(String name) {
        String statement = "SELECT * FROM languages WHERE description = '" +name + "'";
        List<Lesson> lessons = findByCriteria(statement);
        return lessons != null ? lessons.get(0) : null;
    }


    private int updateByCriteria(String sql) {
        try {
            return getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Lesson create(int id, Skill skill, Date start, Date end, Status status) {
        String sql = "INSERT INTO lessons (id,skill_id,start_date,end_date,status_id) " +
                "VALUES('"+ String.valueOf(id) + "','" + skill + "','" + start + "','" + end + "','" + status + "')";
        return updateByCriteria(sql) > 0 ? new Lesson(id, skill,start,end,status) : null;
    }

    public boolean update(Lesson lesson) {
        String sql = "UPDATE lessons SET skill_id = '" + lesson.getSkill() +
                "' WHERE id = " + String.valueOf(lesson.getId());
        return updateByCriteria(sql) > 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM products WHERE id = " + String.valueOf(id);
        return updateByCriteria(sql) > 0;
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
