package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Language {
    private int id;
    private String description;


    public Language(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Language() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
