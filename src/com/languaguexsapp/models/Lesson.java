package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Lesson {
    private int id;
    private Skill skill;
    private Date dateStart;
    private Date dateEnd;
    private Status status;

    public Lesson(int id, Skill skill, Date dateStart, Date dateEnd, Status status) {
        this.id = id;
        this.skill = skill;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
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
