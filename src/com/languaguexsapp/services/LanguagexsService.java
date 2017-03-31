package com.languaguexsapp.services;

import com.languaguexsapp.models.*;

import java.sql.Connection;
import java.util.ArrayList;
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
    private GeneralEntity generalEntity;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public LanguagesEntity getLanguagesEntity() {
        if(connection != null) {
            if(languagesEntity == null) {
                languagesEntity = new LanguagesEntity();
                languagesEntity.setConnection(getConnection());
                languagesEntity.setGeneralEntity(getGeneralEntity());
            }
        }
        return languagesEntity;
    }

    public LessonStudentsEntity getLessonStudentsEntity() {
        if(connection != null) {
            if(lessonStudentsEntity == null) {
                lessonStudentsEntity = new LessonStudentsEntity();
                lessonStudentsEntity.setConnection(getConnection());
                lessonStudentsEntity.setLessonsEntity(getLessonsEntity());
                lessonStudentsEntity.setPeopleEntity(getPeopleEntity());
            }
        }
        return lessonStudentsEntity;
    }

    public LessonsEntity getLessonsEntity() {
        if(connection != null) {
            if(lessonsEntity == null) {
                lessonsEntity = new LessonsEntity();
                lessonsEntity.setConnection(getConnection());
                lessonsEntity.setSkillsEntity(getSkillsEntity());
                lessonsEntity.setStatusEntity(getStatusEntity());
                lessonsEntity.setGeneralEntity(getGeneralEntity());
            }
        }
        return lessonsEntity;
    }

    public LevelsEntity getLevelsEntity() {
        if(connection != null) {
            if(levelsEntity == null) {
                levelsEntity = new LevelsEntity();
                levelsEntity.setConnection(getConnection());
                levelsEntity.setGeneralEntity(getGeneralEntity());
            }
        }
        return levelsEntity;
    }

    public PeopleEntity getPeopleEntity() {
        if(connection != null) {
            if(peopleEntity == null) {
                peopleEntity = new PeopleEntity();
                peopleEntity.setConnection(getConnection());
                peopleEntity.setStatusEntity(getStatusEntity());
                peopleEntity.setGeneralEntity(getGeneralEntity());
            }
        }
        return peopleEntity;
    }

    public SkillsEntity getSkillsEntity() {
        if(connection != null) {
            if(skillsEntity == null) {
                skillsEntity = new SkillsEntity();
                skillsEntity.setConnection(getConnection());
                skillsEntity.setPeopleEntity(getPeopleEntity());
                skillsEntity.setLanguagesEntity(getLanguagesEntity());
                skillsEntity.setLevelsEntity(getLevelsEntity());
                skillsEntity.setGeneralEntity(getGeneralEntity());
            }
        }
        return skillsEntity;
    }

    public StatusEntity getStatusEntity() {
        if(connection != null) {
            if(statusEntity == null) {
                statusEntity = new StatusEntity();
                statusEntity.setConnection(getConnection());
            }
        }
        return statusEntity;
    }

    public GeneralEntity getGeneralEntity() {
        if(connection != null) {
            if(generalEntity == null) {
                generalEntity =new GeneralEntity();
                generalEntity.setConnection(getConnection());
            }
        }
        return generalEntity;
    }

    public List<Language> findAllLanguages() {
        return getLanguagesEntity().findAll();
    }



    public Language findLanguageById(int id) {
        return getLanguagesEntity().findById(id);
    }

    public List<LessonStudent> findAllLessonStudents(){return getLessonStudentsEntity().findAll();}

    public  LessonStudent findLessonStudentById(int idLesson,int idPerson) {
        return getLessonStudentsEntity().findById(idLesson,idPerson);
    }
    public List<LessonStudent> findAllLessonsByPersonId(int personId) {
        return getLessonStudentsEntity().findAllLessonsByPersonId(personId);
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

    public Person findPersonByEmail(String email) {
        return getPeopleEntity().findByEmail(email);
    }

    public Person findPersonById(int id) {
        return getPeopleEntity().findById(id);
    }

    public List<Skill> findAllSkillsByPersonId(int personId) {
        return getSkillsEntity().findAllByPersonId(personId);
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

    public List<Lesson> findAllLessonSkillsById(int personId){
        List<Skill> skills = getSkillsEntity().findAllByPersonId(personId);

        List<Lesson> lessons = new ArrayList<>();

        for(Skill skill:skills) {
            List<Lesson> lessons_auxil=getLessonsEntity().findAllBySkillId(skill.getId());

            for(Lesson lesson:lessons_auxil){
                lessons.add(lesson);
            }
        }

        return lessons;
    }

    public Status findStatusById(int id) {
        return getStatusEntity().findById(id);
    }

    public Person addPerson(Person person){
        return getPeopleEntity().create(person.getFirstName(),person.getLastName(),person.getEmail(),person.getPassword(),1);
    }

    public Language addLanguage(Language language){
        return getLanguagesEntity().create(language.getDescription());
    }

    public Skill addSkill(Skill skill){
        return getSkillsEntity().create(skill.getPerson().getId(),skill.getLanguage().getId(),skill.getLevel().getId(),skill.getPrice());
    }

    public Lesson addLesson(Lesson lesson){
        return getLessonsEntity().create(lesson.getSkill().getId(),lesson.getStartDate(),lesson.getEndDate(),1);
    }
}
