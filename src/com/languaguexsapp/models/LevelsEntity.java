package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LevelsEntity extends BaseEntity{
    public LevelsEntity() {
        super("levels");
    }

    public List<Level> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
    }

    private List<Level> findByCriteria(String sql) {
        List<Level> levels = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while(rs.next()) {
                Level level = Level.build(rs);
                levels.add(level);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return levels;
    }

    public Level findById(int id) {
        String statement = "SELECT * FROM levels WHERE id = " +
                String.valueOf(id);
        List<Level> levels = findByCriteria(statement);
        return levels != null ? levels.get(0) : null;
    }

    public Level findByName(String name) {
        String statement = "SELECT * FROM levels WHERE description = '" +
                name + "'";
        List<Level> levels = findByCriteria(statement);
        return levels != null ? levels.get(0) : null;
    }

    private int updateByCriteria(String sql) {
        try {
            return getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Level create(int id, String name) {
        String sql = "INSERT INTO levels(id, description) " +
                "VALUES(" + String.valueOf(id) + ", '" + name + "')";
        return updateByCriteria(sql) > 0 ? new Level(id, name) : null;
    }

    public boolean update(Level level) {
        String sql = "UPDATE levels SET description = '" + level.getName() +
                "' WHERE id = " + String.valueOf(level.getId());
        return updateByCriteria(sql) > 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM levels WHERE id = " + String.valueOf(id);
        return updateByCriteria(sql) > 0;
    }

}
