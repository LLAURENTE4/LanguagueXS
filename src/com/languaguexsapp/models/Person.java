package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Frank on 25/02/2017.
 */
public class Person {
    private int id;
    private String nameFirst;
    private String nameLast;
    private String email;
    private String user;
    private String password;
    private Date dateRegistration;
    private String type;
    private State state;

    public Person(int id, String nameFirst, String nameLast, String email, String user, String password, Date dateRegistration, String type, State state) {
        this.id = id;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.email = email;
        this.user = user;
        this.password = password;
        this.dateRegistration = dateRegistration;
        this.type = type;
        this.state = state;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public static Person build(ResultSet resultSet, StatesEntity statesEntity) {
        try {
            return new Person(resultSet.getInt("person_id"),resultSet.getString("first_name"),
                             resultSet.getString("last_name"),resultSet.getString("email"),
                            resultSet.getString("user"),resultSet.getString("password"),
                            resultSet.getDate("registration_date"),resultSet.getString("type_person"),
                            statesEntity.findById(resultSet.getInt("status_id"))
                            );
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
