package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Person {
    private int id;
    private String nameFirst;
    private String nameLast;
    private String email;
    private String password;
    private Date dateRegistration;
    private Status status;

    public Person(int id, String nameFirst, String nameLast, String email, String password, Date dateRegistration, Status status) {
        this.id = id;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.email = email;
        this.password = password;
        this.dateRegistration = dateRegistration;
        this.status = status;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    public void setNameLast(String nameLast) {
        this.nameLast = nameLast;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static Person build(ResultSet resultSet, StatusEntity statesEntity) {
        try {
            return new Person(  resultSet.getInt("id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getDate("registration_date"),
                                statesEntity.findById(resultSet.getInt("status_id"))
            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
