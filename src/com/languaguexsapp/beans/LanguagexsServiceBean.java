package com.languaguexsapp.beans;

import com.languaguexsapp.models.*;
import com.languaguexsapp.services.LanguagexsService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
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
    private Language language;
    private Level level;
    private Skill skill;
    private Lesson lesson;

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
            level=new Level();
            lesson=new Lesson();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getPeople() { return service.findAllPeople(); }

    public List<Level> getLevels(){
        return  service.findAllLevels();
    }
    public List<Skill> getSkills(){
        return  service.findAllSkills();
    }
    public List<Skill> getSkillsByPersonId(){
        return  service.findAllSkillsByPersonId(person.getId());
    }

    public List<Lesson> getLessons(){ return service.findAllLessons();}

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

    public String loginPerson() {
        Person personaAuxiliary = new Person();

        personaAuxiliary = service.findPersonByEmail(person.getEmail());
        // if( person.getPassword().equals(personaAuxiliary.getPassword()) ){
        if (personaAuxiliary.getEmail() != null) {
            if (personaAuxiliary.getPassword().equals(person.getPassword())) {
                person = personaAuxiliary;
                this.message = "";
                return "success";
            } else {
                this.message = "Incorrect data";
                return "error";

            }
        } else {
            this.message = "Usuario o contraseña no existen";
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
        skill.setPerson(person);
        skill.setLanguage(language);
        skill.setLevel(level);
        Skill skillAuxiliary = new Skill();
        skillAuxiliary=service.addSkill(skill);
        if( skillAuxiliary.getId() > 0 ){
            skill=null;
            skill=new Skill();
            language=null;
            language=new Language();
            level=null;
            level=new Level();
            this.message="";
            return "success";
        }else{
            this.message="Incorrect data";
            return "error";
        }
    }

    public String registerLesson(){
        lesson.setSkill(skill);
        Lesson lessonAuxiliary = new Lesson();
        lessonAuxiliary=service.addLesson(lesson);
        if( lessonAuxiliary.getId() > 0 ){
            skill=null;
            skill=new Skill();
            lesson=null;
            lesson=new Lesson();
            this.message="";
            return "success";
        }else{
            this.message="Incorrect data";
            return "error";
        }
    }

    public String registerLanguage(){
        Language languageAuxiliary=new Language();
        languageAuxiliary=service.addLanguage(language);
        if( languageAuxiliary.getId() > 0 ){
            language=null;
            language=new Language();
            this.message="";
            return "success";
        }else{
            this.message="Incorrect data";
            return "error";
        }
    }

    /*Menu*/
    public String homeDefault() {
        return "success";
    }

    public String addLanguage() {
        return "success";
    }

    public String closeSession(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(false);
        HttpSession httpSession = (HttpSession) session;
        httpSession.invalidate();
        return "success";
    }


    /*Current data*/
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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Skill getSkill() {
        return skill;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
