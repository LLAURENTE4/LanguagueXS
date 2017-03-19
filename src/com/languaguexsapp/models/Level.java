package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Level {
    private int id;
    private String description;


    public Level(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Level() {
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

    public static Level build(ResultSet resultSet) {
        try {
            return new Level(resultSet.getInt("id"),
                    resultSet.getString("description")
                    );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }



}
