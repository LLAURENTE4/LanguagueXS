package com.languaguexsapp.services;

import com.languaguexsapp.models.*;

import java.sql.Connection;
import java.util.List;

public class LanguagexsService {
    private Connection connection;
    private LanguagesEntity languagesEntity;
    private LessonStudentsEntity lessonStudentsEntity;
    private LessonsEntity lessonsEntity;
    private LevelsEntity levelsEntity;
    private PeopleEntity peopleEntity;
    private SkillsEntity skillsEntity;
    private StatusEntity statusEntity;

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

    protected LessonStudentsEntity getLessonStudentsEntity() {
        if(connection != null) {
            if(lessonStudentsEntity == null) {
                lessonStudentsEntity = new LessonStudentsEntity();
                lessonStudentsEntity.setConnection(getConnection());
            }
        }
        return lessonStudentsEntity;
    }

    protected LessonsEntity getLessonsEntity() {
        if(connection != null) {
            if(lessonsEntity == null) {
                lessonsEntity = new LessonsEntity();
                lessonsEntity.setConnection(getConnection());
            }
        }
        return lessonsEntity;
    }

    protected LevelsEntity getLevelsEntity() {
        if(connection != null) {
            if(levelsEntity == null) {
                levelsEntity = new LevelsEntity();
                levelsEntity.setConnection(getConnection());
            }
        }
        return levelsEntity;
    }

    protected PeopleEntity getPeopleEntity() {
        if(connection != null) {
            if(peopleEntity == null) {
                peopleEntity = new PeopleEntity();
                peopleEntity.setConnection(getConnection());
            }
        }
        return peopleEntity;
    }

    protected SkillsEntity getSkillsEntity() {
        if(connection != null) {
            if(skillsEntity == null) {
                skillsEntity = new SkillsEntity();
                skillsEntity.setConnection(getConnection());
            }
        }
        return skillsEntity;
    }

    protected StatusEntity getStatusEntity() {
        if(connection != null) {
            if(statusEntity == null) {
                statusEntity = new StatusEntity();
                statusEntity.setConnection(getConnection());
            }
        }
        return statusEntity;
    }


    protected void setLanguagesEntity(LanguagesEntity languagesEntity) {
        this.languagesEntity = languagesEntity;
    }

    protected void setLessonStudentsEntity(LessonStudentsEntity lessonStudentsEntity) {
        this.lessonStudentsEntity = lessonStudentsEntity;
    }

    protected void setLessonsEntity(LessonsEntity lessonsEntity) {
        this.lessonsEntity = lessonsEntity;
    }

    protected void setLevelsEntity(LevelsEntity levelsEntity) {
        this.levelsEntity = levelsEntity;
    }

    protected void setPeopleEntity(PeopleEntity peopleEntity) {
        this.peopleEntity = peopleEntity;
    }

    protected void setSkillsEntity(SkillsEntity skillsEntity) {
        this.skillsEntity = skillsEntity;
    }

    protected void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
    }



    public List<Language> findAllLanguages() {
        return getLanguagesEntity().findAll();
    }

    public Language findLanguageById(int id) {
        return getLanguagesEntity().findById(id);
    }

    public List<LessonStudent> findAllLessonStudents(){return getLessonStudentsEntity().findAll();}

    public LessonStudent findLessonStudentById(int idLesson,int idPerson) {
        return getLessonStudentsEntity().findById(idLesson,idPerson);
    }

    public List<Lesson> findAllLessons() {
        return getLessonsEntity().findAll();
    }

    public Lesson findLessonById(int id) {
        return getLessonsEntity().findById(id);
    }

    public List<Level> findAllLevels() {
        return getLevelsEntity().findAll();
    }

    public Level findLevelById(int id) {
        return getLevelsEntity().findById(id);
    }

    public List<Person> findAllPeople() {
        return getPeopleEntity().findAll();
    }

    public Person findPersonById(int id) {
        return getPeopleEntity().findById(id);
    }

    public List<Skill> findAllSkills() {
        return getSkillsEntity().findAll();
    }

    public Skill findSkillById(int id) {
        return getSkillsEntity().findById(id);
    }

    public List<Status> findAllStatus() {
        return getStatusEntity().findAll();
    }

    public Status findStatusById(int id) {
        return getStatusEntity().findById(id);
    }

}
