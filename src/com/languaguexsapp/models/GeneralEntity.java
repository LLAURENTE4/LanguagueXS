package com.languaguexsapp.models;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Frank on 05/03/2017.
 */
public class GeneralEntity extends BaseEntity{

    public GeneralEntity() {
        super("tables");
    }

    public Date getCurrentDate() {
        String sql = "SELECT now() as current_date_server";

        Date currentDate = null;
        try {
            ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
            if (resultSet.next()) currentDate = resultSet.getDate("current_date_server");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentDate;
    }

    public int getIdTable(String name) {
        String sql = "{call sp_getIdTable (?,?)}";
        int idTable=0;
        try {
            CallableStatement cst = getConnection().prepareCall(sql);
            cst.setString(1, name);
            cst.registerOutParameter(2, java.sql.Types.INTEGER);
            cst.execute();

            return cst.getInt(2)>0 ? cst.getInt(2): 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}