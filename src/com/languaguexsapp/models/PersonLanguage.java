package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Frank on 25/02/2017.
 */
public class PersonLanguage {
    private int id;
    private Person person;
    private Language language;
    private Level level;
    private float price;

    public PersonLanguage(int id, Person person, Language language, Level level, float price) {
        this.id = id;
        this.person = person;
        this.language = language;
        this.level = level;
        this.price = price;
    }

    public PersonLanguage() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public static PersonLanguage build(ResultSet resultSet, PeopleEntity peopleEntity, LanguagesEntity languagesEntity, LevelsEntity levelsEntity) {

        try {
            return new PersonLanguage(resultSet.getInt("person_language_id"),
                                    peopleEntity.findById(resultSet.getInt("person_id")),
                                    languagesEntity.findById(resultSet.getInt("language_id")),
                                    levelsEntity.findById(resultSet.getInt("level_id")),
                                    resultSet.getFloat("price")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
