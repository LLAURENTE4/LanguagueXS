package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import java.util.Date;
import java.util.List;

public class PeopleEntity extends BaseEntity{
    private StatusEntity statusEntity;

    public PeopleEntity() {
        super("people");
    }

    public List<Person> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
    }

    private List<Person> findByCriteria(String sql) {
        List<Person> people = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while(rs.next()) {
                Person person = Person.build(rs,getStatusEntity());
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public Person findById(int id) {
        String statement = "SELECT id, first_name, last_name,email,user_name,password,registration_date,status_id FROM people p" +
                " inner join status s on p.status_id=s.id WHERE p.id = " +String.valueOf(id);
        List<Person> people = findByCriteria(statement);
        return people != null ? people.get(0) : null;
    }
    private int updateByCriteria(String sql) {
        try {
            return getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Person create(int id, String first_name, String last_name, String email, String username, String password, Date registration_date, int status_id) {
        String sql = "INSERT INTO people(id, first_name, last_name,email,user_name,password,registration_date,status_id) " +
                "VALUES('" + id + "', '" + first_name + "', '"+ last_name + "','"+ email   +"','"+ username   +"','"+ password   +"','"+ registration_date   +"'" +
                ","+ String.valueOf( status_id )  +")";
        return updateByCriteria(sql) > 0 ? new Person(id, first_name, last_name,email,username,password, registration_date, getStatusEntity().findById(status_id)) : null;
    }

    public boolean update(Person person) {
        String sql = "UPDATE people SET first_name = '" + person.getNameFirst() + "',last_name= '" + person.getNameLast() + "',email='" + person.getEmail() + "',"
                 + "password='" + person.getPassword() + "'"+
                " WHERE id = '" + person.getId() + "'";
        return updateByCriteria(sql) > 0;
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM people WHERE id = '" + id + "'";
        return updateByCriteria(sql) > 0;
    }

    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statesEntity) {
        this.statusEntity = statesEntity;
    }
}
