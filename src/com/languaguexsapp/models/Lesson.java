package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Lesson {
    private int id;
    private Skill skill;
    private Date startDate;
    private Date endDate;
    private Status status;

    public Lesson(int id, Skill skill, Date startDate, Date endDate, Status status) {
        this.id = id;
        this.skill = skill;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public Lesson() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static Lesson build(ResultSet resultSet, SkillsEntity skillsEntity, StatusEntity statusEntity ) {
        try {
            return new Lesson(
                    resultSet.getInt("id"),
                    skillsEntity.findById( resultSet.getInt("skill_id")),
                    resultSet.getDate("start_date"),
                    resultSet.getDate("end_date"),
                    statusEntity.findById( resultSet.getInt("status_id"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}
