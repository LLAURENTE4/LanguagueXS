package com.languaguexsapp.models;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * Created by j.quezada.vergaray on 02-Mar-17.
 */
public class People {
    private int id;
    private String nameFirst;
    private String nameLast;
    private String email;
    private String user;
    private String password;
    private Date dateRegistration;
    private String type;
    private Status status;

    public People(int id, String nameFirst, String nameLast, String email, String user, String password, Date dateRegistration, String type, Status status) {
        this.id = id;
        this.nameFirst = nameFirst;
        this.nameLast = nameLast;
        this.email = email;
        this.user = user;
        this.password = password;
        this.dateRegistration = dateRegistration;
        this.type = type;
        this.status = status;
    }

    public People() {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status state) {
        this.status = status;
    }

    public static People build(ResultSet resultSet, StatusEntity statesEntity) {
        try {
            return new People(resultSet.getInt("person_id"),resultSet.getString("first_name"),
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
