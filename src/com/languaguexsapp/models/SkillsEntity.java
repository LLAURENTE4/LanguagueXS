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




    private int updateByCriteria(String sql) {
        try {
            return getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Skill create(int id,int personId,int languageId, int levelId,Double price )
    {
        String sql = "INSERT INTO skill(id,person_id, language_id,level_id, price) " +
                "VALUES(" + String.valueOf(id) + "," + String.valueOf( personId) + ","+ String.valueOf( languageId) +","+String.valueOf( levelId)
                +","+ String.valueOf( price) +")";
        return updateByCriteria(sql) > 0 ? new Skill(id,getPeopleEntity().findById(personId),getLanguagesEntity().findById( languageId),
              getLevelsEntity().findById(  levelId),price) : null;
    }

    public boolean update(Skill skill) {
        String sql = "UPDATE skill SET language_id = '" + String.valueOf( skill.getId()) +
                "',level_id=" + String.valueOf( skill.getLevel()) + ",price=" + String.valueOf( skill.getPrice())
                + "WHERE region_id = " + String.valueOf(skill.getId());
        return updateByCriteria(sql) > 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM skills WHERE region_id = " + String.valueOf(id);
        return updateByCriteria(sql) > 0;
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
