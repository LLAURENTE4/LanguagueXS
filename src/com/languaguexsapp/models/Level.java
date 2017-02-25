package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Frank on 25/02/2017.
 */
public class Level {
    private int id;
    private String name;

    public Level(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Level() {
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

    public static Level build(ResultSet resultSet) {
        try {
            return new Level(resultSet.getInt("level_id"),
                    resultSet.getString("description")
                    );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }



}
