package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 25/02/2017.
 */
public class PeopleEntity extends BaseEntity{
    private StatusEntity statusEntity;

    public PeopleEntity() {
        super("people");
    }

    public List<People> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
    }

    private List<People> findByCriteria(String sql) {
        List<People> people = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while(rs.next()) {
                People person = People.build(rs,getStatusEntity());
                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return people;
    }

    public People findById(int id) {
        String statement = "SELECT * FROM person WHERE person_id = " +
                String.valueOf(id);
        List<People> people = findByCriteria(statement);
        return people != null ? people.get(0) : null;
    }

    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statesEntity) {
        this.statusEntity = statesEntity;
    }
}
