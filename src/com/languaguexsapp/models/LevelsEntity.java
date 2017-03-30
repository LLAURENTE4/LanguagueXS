package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LevelsEntity extends BaseEntity{
    private GeneralEntity generalEntity;

    public LevelsEntity() {
        super("levels");
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

    private int updateByCriteria(String sql) {
        try {
            return getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Level> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
    }

    public Level findById(int id) {
        String statement = "SELECT * FROM levels WHERE id = " + String.valueOf(id);
        List<Level> levels = findByCriteria(statement);
        return levels != null ? levels.get(0) : null;
    }

    public Level findByDescription(String description) {
        String statement = "SELECT * FROM levels WHERE description = '" + description + "'";
        List<Level> levels = findByCriteria(statement);
        return levels != null ? levels.get(0) : null;
    }

    public Level create(String description) {
        int id= getGeneralEntity().getIdTable(getTableName());
        String sql = "INSERT INTO levels(id, description) " +
                "VALUES(" + String.valueOf(id) + ", '" + description + "')";
        return updateByCriteria(sql) > 0 ? new Level(id, description) : null;
    }

    public boolean update(Level level) {
        String sql = "UPDATE levels SET description = '" + level.getDescription() +
                "' WHERE id = " + String.valueOf(level.getId());
        return updateByCriteria(sql) > 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM levels WHERE id = " + String.valueOf(id);
        return updateByCriteria(sql) > 0;
    }

    public GeneralEntity getGeneralEntity() {
        return generalEntity;
    }

}
