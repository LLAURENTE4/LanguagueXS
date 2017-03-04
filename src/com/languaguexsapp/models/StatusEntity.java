package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusEntity extends BaseEntity{
    public StatusEntity() {
        super("status");
    }

    public List<Status> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
    }

    private List<Status> findByCriteria(String sql) {
        List<Status> statuss = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while(rs.next()) {
                Status status = Status.build(rs);
                statuss.add(status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statuss;
    }

    public Status findById(int id) {
        String statement = "SELECT * FROM status WHERE id = " +
                String.valueOf(id);
        List<Status> statuss = findByCriteria(statement);
        return statuss != null ? statuss.get(0) : null;
    }

    public Status findByName(String name) {
        String statement = "SELECT * FROM status WHERE description = '" +
                name + "'";
        List<Status> statuss = findByCriteria(statement);
        return statuss != null ? statuss.get(0) : null;
    }

    private int updateByCriteria(String sql) {
        try {
            return getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Status create(int id, String name) {
        String sql = "INSERT INTO status(id, description) " +
                "VALUES(" + String.valueOf(id) + ", '" + name + "')";
        return updateByCriteria(sql) > 0 ? new Status(id, name) : null;
    }

    public boolean update(Status status) {
        String sql = "UPDATE status SET description = '" + status.getName() +
                "' WHERE id = " + String.valueOf(status.getId());
        return updateByCriteria(sql) > 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM status WHERE id = " + String.valueOf(id);
        return updateByCriteria(sql) > 0;
    }
}
