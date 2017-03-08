package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import java.util.Date;
import java.util.List;

public class PeopleEntity extends BaseEntity{
    private GeneralEntity generalEntity;
    private StatusEntity statusEntity;

    public PeopleEntity() {
        super("people");
    }

    private static String DEFAULT_QUERY =
            "SELECT * FROM people";

    private int loginBy(String condition) {
        String sql = DEFAULT_QUERY + " " + condition;
        try {
            return getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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

    public Person create(String first_name, String last_name, String email, String username, String password, int status_id) {
        int id= getGeneralEntity().getIdTable(getTableName());

        String sql = "INSERT INTO people(id, first_name, last_name,email,user_name,password,registration_date,status_id) " +
                "VALUES(" + String.valueOf( id) + ", '" + first_name + "', '"+ last_name + "','"+ email   +"','"+ username   +"','"+ password   +"','"+String.valueOf(getGeneralEntity().getDateCurrent())+"'" +
                ","+ String.valueOf( status_id )  +")";
        return updateByCriteria(sql) > 0 ? new Person(id, first_name, last_name,email,username,password, getGeneralEntity().getDateCurrent(), getStatusEntity().findById(status_id)) : null;
    }

    public Person getValues(String condition_name, String value){
        String statement = "SELECT p.first_name, p.last_name, l.description FROM people p " +
                "inner join skills s on p.id = s.person_id " +
                "inner join languages l on  s.language_id = l.id " +
                "where " +  condition_name +  " = '" + value + "' and status_id='1'";
        List<Person> people =  findByCriteria(statement);
        return people != null ? people.get(0) : null;
    }

    public Person getValuesbyUsername(String username){
        String condition = "user";
        return getValues(condition,username);
    }

    public Person getValuesbyFirstName(String fn){
        String condition = "first_name";
        return getValues(condition,fn);
    }

    public Person getValuesbyLastName(String ln){
        String condition = "last_name";
        return  getValues(condition,ln);
    }

    public boolean update(Person person) {
        String sql = "UPDATE people SET first_name = '" + person.getNameFirst() + "',last_name= '" + person.getNameLast() + "',email='" + person.getEmail() + "',"
                 + "user='" + person.getUser() + "',password='" + person.getPassword() + "',status_id="+String.valueOf(person.getStatus() )+
                " WHERE id = " + String.valueOf(person.getId());
        return updateByCriteria(sql) > 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM people WHERE id = " + String.valueOf(id) ;
        return updateByCriteria(sql) > 0;
    }

    public boolean loginByEmail(String email,String password) {
        String sql = "WHERE email= '" + email + "' AND password='" + password + "'";
        return loginBy(sql) > 0;
    }

    public boolean loginByUsername(String username,String password){
        String sql = "WHERE user= '" + username + "' AND password='" + password + "'";
        return loginBy(sql) > 0;
    }



    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statesEntity) {
        this.statusEntity = statesEntity;
    }

    public GeneralEntity getGeneralEntity() {
        return generalEntity;
    }

    public void setGeneralEntity(GeneralEntity generalEntity) {
        this.generalEntity = generalEntity;
    }
}
