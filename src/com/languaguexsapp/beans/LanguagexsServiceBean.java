package com.languaguexsapp.beans;

import com.languaguexsapp.models.*;
import com.languaguexsapp.services.LanguagexsService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Frank on 12/03/2017.
 */
@ManagedBean(name = "service", eager = true)
@SessionScoped
public class LanguagexsServiceBean {
    private LanguagexsService service;
    private Person person;
    private String message;
    private String pageHome;
    private Language language;
    private Skill skill;

    public LanguagexsServiceBean() {
        try {
            InitialContext ctx = new InitialContext();
            Connection connection = ((DataSource) ctx
                    .lookup("jdbc/MySQLDataSource_LanguageXS"))
                    .getConnection();
            service = new LanguagexsService();
            service.setConnection(connection);
            person=new Person();
            language=new Language();
            skill=new Skill();
            pageHome="home_default.xhtml";
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getPeople() { return service.findAllPeople(); }

    public  List<Level> getLevels(){
        return  service.findAllLevels();
    }
    public  List<Skill> getSkills(){
        return  service.findAllSkills();
    }

    public List<Language> getLanguages(){return service.findAllLanguages();}

    public  List<LessonStudent> getLessonStudents(){
        return  service.findAllLessonStudents();
    }

    public  List<LessonStudent> getLessonStudentsById(){
      return service.findAllLessonsByPersonId(person.getId());
    }

    public String listPeople() { return "success";}
    public String listLevels() { return "success";}
    public String listLessonStudents() { return "success";}
    public String listSkills() { return "success";}

    public List<Lesson> getLessonSkillsById(){
        return service.findAllLessonSkillsById(person.getId());
    }

    public String loginPerson(){
        Person personaAuxiliary=new Person();

        personaAuxiliary=service.findPersonByEmail(person.getEmail());
        if( person.getPassword().equals(personaAuxiliary.getPassword())  &&  personaAuxiliary.getStatus().getId() == 1 &&  personaAuxiliary.getId() > 0 ){
            person=personaAuxiliary;
            this.message="";
            return "success";
        }else{
            this.message="Incorrect data";
            return "error";
        }
    }

    public String registerPerson(){
        Person personaAuxiliary = new Person();
        personaAuxiliary=service.addPerson(person);
        if( personaAuxiliary.getId() > 0 ){
            person=personaAuxiliary;
            this.message="";
            return "success";
        }else{
            this.message="Incorrect data";
            return "error";
        }
    }
    public String registerSkill(){
        Skill SkillAuxiliary = new Skill();
        SkillAuxiliary=service.addSkill(skill);
        if( SkillAuxiliary.getId() > 0 ){
            this.message="";
            setPageHome("home_default.xhtml");
            return "success";
        }else{
            this.message="Incorrect data";
            setPageHome("register_language.xhtml");
            return "error";
        }
    }
    public String registerLanguage(){
        Language languageAuxiliary=new Language();
        languageAuxiliary=service.addLanguage(language);
        if( languageAuxiliary.getId() > 0 ){
            this.message="";
            setPageHome("home_default.xhtml");
            return "success";
        }else{
            this.message="Incorrect data";
            setPageHome("register_language.xhtml");
            return "error";
        }

    }

    public String closeSession(){
        person=null;
        person=new Person();
        return "success";
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPageHome() {
        return pageHome;
    }

    public void setPageHome(String pageHome) {
        this.pageHome = pageHome;
    }

    public Language getLanguage() {
        return language;
    }
    public Skill getSkill() {
        return skill;
    }
    public void setLanguage(Language language) {
        this.language = language;
    }
}
