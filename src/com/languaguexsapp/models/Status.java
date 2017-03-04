package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Status {
    private int id;
    private String name;

    public Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Status() {
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

    public static Status build(ResultSet resultSet) {
        try {
            return new Status(resultSet.getInt("id"),resultSet.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
