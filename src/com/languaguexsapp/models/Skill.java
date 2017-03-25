package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Skill {

    private int id;
    private Person person;
    private Language language;
    private Level level;
    private Double price;

    public Skill(int id, Person person, Language language, Level level, Double price) {
        this.id = id;
        this.person = person;
        this.language = language;
        this.level = level;
        this.price = price;
    }

    public Skill() {}


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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static Skill build(ResultSet resultSet, PeopleEntity peopleEntity, LanguagesEntity languagesEntity, LevelsEntity levelsEntity ) {
        try {
            return new Skill(
                                resultSet.getInt("id"),
                                peopleEntity.findById( resultSet.getInt("person_id")),
                                languagesEntity.findById( resultSet.getInt("language_id")),
                                levelsEntity.findById( resultSet.getInt("level_id")),
                                resultSet.getDouble("price")
                            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

}


