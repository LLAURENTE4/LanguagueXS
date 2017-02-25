package com.languaguexsapp.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Frank on 25/02/2017.
 */
public class LanguagesEntity  extends BaseEntity{
    public LanguagesEntity() {
        super("language");
    }

    public List<Language> findAll() {
        String statement = getDefaultStatement() + getTableName();
        return findByCriteria(statement);
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

    public Language findById(int id) {
        String statement = "SELECT * FROM language WHERE language_id = " +
                String.valueOf(id);
        List<Language> languages = findByCriteria(statement);
        return languages != null ? languages.get(0) : null;
    }

    public Language findByName(String name) {
        String statement = "SELECT * FROM language WHERE language_name = '" +
                name + "'";
        List<Language> languages = findByCriteria(statement);
        return languages != null ? languages.get(0) : null;
    }

    private int updateByCriteria(String sql) {
        try {
            return getConnection().createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Language create(int id, String name) {
        String sql = "INSERT INTO languages(language_id, language_name) " +
                "VALUES(" + String.valueOf(id) + ", '" + name + "')";
        return updateByCriteria(sql) > 0 ? new Language(id, name) : null;
    }

    public boolean update(Language language) {
        String sql = "UPDATE language SET language_name = '" + language.getName() +
                "' WHERE language_id = " + String.valueOf(language.getId());
        return updateByCriteria(sql) > 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM language WHERE language_id = " + String.valueOf(id);
        return updateByCriteria(sql) > 0;
    }

}
