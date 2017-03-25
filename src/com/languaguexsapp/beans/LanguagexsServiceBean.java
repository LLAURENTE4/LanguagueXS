package com.languaguexsapp.beans;

import com.languaguexsapp.models.LessonStudent;
import com.languaguexsapp.models.Level;
import com.languaguexsapp.models.Person;
import com.languaguexsapp.models.Skill;
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

    public LanguagexsServiceBean() {
        try {
            InitialContext ctx = new InitialContext();
            Connection connection = ((DataSource) ctx
                    .lookup("jdbc/MySQLDataSource_LanguageXS"))
                    .getConnection();
            service = new LanguagexsService();
            service.setConnection(connection);
            person=new Person();
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

    public  List<LessonStudent> getLessonStudents(){
        return  service.findAllLessonStudents();
    }

    public String listPeople() { return "success";}
    public String listLevels() { return "success";}
    public String listLessonStudents() { return "success";}
    public String listSkills() { return "success";}

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
}
