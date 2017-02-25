package com.languaguexsapp.services;

import com.languaguexsapp.models.Language;
import com.languaguexsapp.models.LanguagesEntity;

import java.sql.Connection;
import java.util.List;

/**
 * Created by Frank on 25/02/2017.
 */
public class LanguagexsService {
    private Connection connection;
    private LanguagesEntity languagesEntity;

    public LanguagexsService() {
    }

    public LanguagexsService(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    protected LanguagesEntity getLanguagesEntity() {
        if(connection != null) {
            if(languagesEntity == null) {
                languagesEntity = new LanguagesEntity();
                languagesEntity.setConnection(getConnection());
            }
        }
        return languagesEntity;
    }
    protected void setLanguagesEntity(LanguagesEntity languagesEntity) {
        this.languagesEntity = languagesEntity;
    }

    public List<Language> findAllLanguages() {
        return getLanguagesEntity().findAll();
    }

    public Language findLanguageById(int id) {
        return getLanguagesEntity().findById(id);
    }
}
