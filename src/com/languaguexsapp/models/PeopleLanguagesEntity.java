package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 25/02/2017.
 */
public class PeopleLanguagesEntity extends BaseEntity{
    private PeopleEntity peopleEntity;
    private LanguagesEntity languagesEntity;
    private LevelsEntity levelsEntity;

    public PeopleLanguagesEntity() {
        super("people_languages");
    }

    public List<PersonLanguage> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
    }

    private List<PersonLanguage> findByCriteria(String sql) {
        List<PersonLanguage> peoplelanguages = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while(rs.next()) {
                PersonLanguage personLanguage = PersonLanguage.build(rs,getPeopleEntity(),getLanguagesEntity(),getLevelsEntity());
                peoplelanguages.add(personLanguage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return peoplelanguages;
    }

    public PersonLanguage findById(int id) {
        String statement = "SELECT * FROM people_languages WHERE person_language_id = " +
                String.valueOf(id);
        List<PersonLanguage> peoplelanguages = findByCriteria(statement);
        return peoplelanguages != null ? peoplelanguages.get(0) : null;
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