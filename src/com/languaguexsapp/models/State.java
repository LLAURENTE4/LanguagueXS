package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Frank on 25/02/2017.
 */
public class State {
    private int id;
    private String name;

    public State(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public State() {
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
    public static State build(ResultSet resultSet) {
        try {
            return new State(resultSet.getInt("status_id"),resultSet.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
