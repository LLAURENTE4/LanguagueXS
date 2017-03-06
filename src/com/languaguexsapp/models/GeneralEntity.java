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

    public int getIdTable(String name) {

        try {
            CallableStatement cst = getConnection().prepareCall("{call sp_getIdTable (?,?)}");
            cst.setString(1, name);
            cst.registerOutParameter(2, java.sql.Types.INTEGER);

            return cst.getInt(2)>0 && cst.executeUpdate()>0 ? cst.getInt(2): 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Date getDateCurrent(){
        try {
            String sql = "SELECT date_current=now()";
            ResultSet rs = getConnection().createStatement().executeQuery(sql);
            return rs.getRow() != 0 ? rs.getDate("date_current") : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
