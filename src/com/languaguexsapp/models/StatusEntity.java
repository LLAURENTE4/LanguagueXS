package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 25/02/2017.
 */
public class StatusEntity extends BaseEntity{
    public StatusEntity() {
        super("status");
    }

    public List<Status> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
    }

    private List<Status> findByCriteria(String sql) {
        List<Status> statuses = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while(rs.next()) {
                Status status = Status.build(rs);
                statuses.add(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuses;
    }

    public Status findById(int id) {
        String statement = "SELECT * FROM status WHERE status_id = " +
                String.valueOf(id);
        List<Status> statuses = findByCriteria(statement);
        return statuses != null ? statuses.get(0) : null;
    }

    public Status findByName(String name) {
        String statement = "SELECT * FROM status WHERE description = '" +
                name + "'";
        List<Status> statuses = findByCriteria(statement);
        return statuses != null ? statuses.get(0) : null;
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
