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

    public Skill create(Integer personId,Integer languageId, Integer levelId,Double price )
    {
        String sql = "INSERT INTO skills(person_id, language_id,level_id, price) " +
                "VALUES(" + personId + ","+languageId+","+levelId+","+ price +")";
        return updateByCriteria(sql) > 0 ? new Skill(1,personId,languageId,levelId,price) : null;
    }

    public boolean update(skills region) {
        String sql = "UPDATE skills SET region_name = '" + region.getName() +
                "' WHERE region_id = " + String.valueOf(region.getId());
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
