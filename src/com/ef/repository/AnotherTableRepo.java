package com.ef.repository;

import com.ef.config.DBConnection;
import com.ef.model.AnotherTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by umarwhk(umrwhk@gmail.com) on 2019-06-03.
 */
public class AnotherTableRepo {

    public static void save(AnotherTable anotherTable){

        Connection con = null;
        PreparedStatement ps = null;
        String query = "INSERT INTO another_table (ip, comment) values (?,?)";

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(query);

            ps.setString(1, anotherTable.getIp());
            ps.setString(2, anotherTable.getComment());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
