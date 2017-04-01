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

    private int updateByCriteria(String sql) {
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

    public Person findById(int id) {
        String statement = "SELECT id, first_name, last_name,email,password,registration_date,status_id FROM people " +
                " WHERE id = " +String.valueOf(id);
        List<Person> people = findByCriteria(statement);
        return people != null ? people.get(0) : null;
    }

    public Person findByEmail(String email) {
        String statement = "SELECT id, first_name, last_name,email,password,registration_date,status_id FROM people " +
                " WHERE email = '" +String.valueOf(email)+"'";
        List<Person> people = findByCriteria(statement);
        return people.size()>0 ? people.get(0) : null;
        //return null;
    }

    public Person create(String first_name, String last_name, String email, String password, int status_id) {
        int id= getGeneralEntity().getIdTable(getTableName());
        String sql = "INSERT INTO people(id, first_name, last_name,email,password,registration_date,status_id) " +
                "VALUES(" + String.valueOf( id) + ", '" + first_name + "', '"+ last_name + "','"+ email   +"','"+ password   +"','"+String.valueOf(getGeneralEntity().getCurrentDate())+"'" +
                ","+ String.valueOf( status_id )  +")";
        return updateByCriteria(sql) > 0 ? new Person(id, first_name, last_name,email,password, getGeneralEntity().getCurrentDate(), getStatusEntity().findById(status_id)) : null;
    }

    public boolean update(Person person) {
        String sql = "UPDATE people SET first_name = '" + person.getFirstName() + "',"+
                " last_name= '" + person.getLastName() + "',"+
                " email='" + person.getEmail() + "',"+
                " password='" + person.getPassword() + "',"+
                " status_id="+String.valueOf(person.getStatus() )+
                " WHERE id = " + String.valueOf(person.getId());
        return updateByCriteria(sql) > 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM people WHERE id = " + String.valueOf(id) ;
        return updateByCriteria(sql) > 0;
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
