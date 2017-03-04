package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillsEntity extends BaseEntity{
    private PeopleEntity peopleEntity;
    private LanguagesEntity languagesEntity;
    private LevelsEntity levelsEntity;

    public SkillsEntity() {
        super("skills");
    }

    public List<Skill> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
    }

    private List<Skill> findByCriteria(String sql) {
        List<Skill> skills = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while(rs.next()) {
                Skill skill = Skill.build(rs,getPeopleEntity(),getLanguagesEntity(),getLevelsEntity());
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

    public Skill findById(int id) {
        String statement = "SELECT * FROM skills WHERE id = " +String.valueOf(id);
        List<Skill> skill = findByCriteria(statement);
        return skill != null ? skill.get(0) : null;
    }


    public PeopleEntity getPeopleEntity() {
        return peopleEntity;
    }

    public void setPeopleEntity(PeopleEntity peopleEntity) {
        this.peopleEntity = peopleEntity;
    }

    public LanguagesEntity getLanguagesEntity() {
        return languagesEntity;
    }

    public void setLanguagesEntity(LanguagesEntity languagesEntity) {
        this.languagesEntity = languagesEntity;
    }

    public LevelsEntity getLevelsEntity() {
        return levelsEntity;
    }

    public void setLevelsEntity(LevelsEntity levelsEntity) {
        this.levelsEntity = levelsEntity;
    }
}
