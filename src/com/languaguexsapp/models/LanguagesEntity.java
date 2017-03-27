package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LanguagesEntity  extends BaseEntity{
    private GeneralEntity generalEntity;

    public LanguagesEntity() {
        super("languages");
    }

    private List<Language> findByCriteria(String sql) {
        List<Language> languages = new ArrayList<>();
        try {
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            while(rs.next()) {
                Language language = Language.build(rs);
                languages.add(language);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return languages;
    }

    private int updateByCriteria(String sql) {
        try {
            return getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Language> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
    }

    public Language findById(int id) {
        String statement = "SELECT * FROM languages WHERE id = " +
                String.valueOf(id);
        List<Language> languages = findByCriteria(statement);
        return languages != null ? languages.get(0) : null;
    }

    public Language findByDescription(String description) {
        String statement = "SELECT * FROM languages WHERE description = '" +description + "'" ;
        List<Language> languages = findByCriteria(statement);
        return languages != null ? languages.get(0) : null;
    }

    public Language create(String description) {
        int id= getGeneralEntity().getIdTable(getTableName());
        String sql = "INSERT INTO languages(id,description) VALUES("+String.valueOf(id)+",'" + description + "')";
        return updateByCriteria(sql) > 0 ? new Language(id, description) : null;
    }

    public boolean update(Language language) {
        String sql = "UPDATE languages SET description = '" + language.getDescription() +
                "' WHERE id = " + String.valueOf(language.getId());
        return updateByCriteria(sql) > 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM languages WHERE id = " + String.valueOf(id);
        return updateByCriteria(sql) > 0;
    }

    public GeneralEntity getGeneralEntity() {
        return generalEntity;
    }

    public void setGeneralEntity(GeneralEntity generalEntity) {
        this.generalEntity = generalEntity;
    }
}
