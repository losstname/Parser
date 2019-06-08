package com.ef.config;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by umarwhk(umrwhk@gmail.com) on 2019-06-03.
 */
public class DBConnection {

    public static Connection getConnection() {
        Properties props = new Properties();
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/parser?useSSL=false&useUnicode=true&characterEncoding=utf8",
                    "root",
                    "");
        }  catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

}
