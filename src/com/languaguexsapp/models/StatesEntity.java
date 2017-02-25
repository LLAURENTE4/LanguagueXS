package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 25/02/2017.
 */
public class StatesEntity extends BaseEntity{
    public StatesEntity() {
        super("status");
    }

    public List<State> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
    }

    private List<State> findByCriteria(String sql) {
        List<State> states = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while(rs.next()) {
                State state = State.build(rs);
                states.add(state);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return states;
    }

    public State findById(int id) {
        String statement = "SELECT * FROM status WHERE status_id = " +
                String.valueOf(id);
        List<State> states = findByCriteria(statement);
        return states != null ? states.get(0) : null;
    }

    public State findByName(String name) {
        String statement = "SELECT * FROM status WHERE description = '" +
                name + "'";
        List<State> states = findByCriteria(statement);
        return states != null ? states.get(0) : null;
    }

    private int updateByCriteria(String sql) {
        try {
            return getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}