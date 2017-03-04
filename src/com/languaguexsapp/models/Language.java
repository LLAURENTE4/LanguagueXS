package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Language {
    private int id;
    private String name;


    public Language(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Language() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static Language build(ResultSet resultSet) {
        try {
            return new Language(resultSet.getInt("id"),
                    resultSet.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
