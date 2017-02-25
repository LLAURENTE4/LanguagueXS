package com.languaguexsapp.controllers;

//import org.atteo.evo.inflector.English;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by laurente on 25/02/2017.
 */
@WebServlet(name = "BaseController")
public class BaseController extends HttpServlet{

    private String entityName;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected Connection getConnection() {
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/MySQLDataSource");
            return ds.getConnection();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getUrlForAction(String action) {
        action = action.toLowerCase().trim();
        if(Arrays.asList(new  String[] {"index", "create", "update", "delete"}).contains(action)) {
            return "list"
                    //+ English.plural(getEntityName())
            + ".jsp";
        }
        if(Arrays.asList(new String[] {"show", "new", "edit"}).contains(action)) {
            return action + getEntityName() + ".jsp";
        }
        return null;
    }

}
