package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by laurente on 03/03/2017.
 */
public class Skills {

    private int id;
    private Person person;
    private Languages lenguague;
    private Levels level;
    private double price;

    public Skills(int id, Person person, Languages lenguague, Levels level, double price) {
        this.id = id;
        this.person = person;
        this.lenguague = lenguague;
        this.level = level;
        this.price = price;
    }

    public Skills() {

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

    public Languages getLenguague() {
        return lenguague;
    }

    public void setLenguague(Languages lenguague) {
        this.lenguague = lenguague;
    }

    public Levels getLevel() {
        return level;
    }

    public void setLevel(Levels level) {
        this.level = level;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public static Lesson build(ResultSet resultSet, PeopleEntity peopleEntity, LanguagesEntity languagesEntity, LevelsEntity levelsEntity ) {
        try {
            return new Lesson(

                    resultSet.getInt("id"),
                    peopleEntity.findById( resultSet.getInt("person_id")),
                    languagesEntity.findById( resultSet.getInt("languague_id")),
                    levelsEntity.findById( resultSet.getInt("level_id")),
                    resultSet.getDouble("price")
            )
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }




}
