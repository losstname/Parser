package com.ef.repository;

import com.ef.config.DBConnection;
import com.ef.model.AccessLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by umarwhk(umrwhk@gmail.com) on 2019-06-03.
 */
public class AccessLogRepo {

    public static void save(AccessLog log){
        Connection con = null;
        PreparedStatement ps = null;
        String query = "INSERT INTO access_log (date_time, ip, request, request_status, user_agent) values (?,?,?,?,?)";

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(query);

            ps.setString(1, log.getDateTime().toString());
            ps.setString(2, log.getIp());
            ps.setString(3, log.getRequest());
            ps.setInt(4, log.getRequestStatus());
            ps.setString(5, log.getUserAgent());

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

    public static List<String> ipExceedThreshold(LocalDateTime startDateTime, LocalDateTime endDateTime, Integer threshold){
        List<String> ips = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        String query = "SELECT *, COUNT(ip) AS hit FROM access_log WHERE (date_time BETWEEN ? AND ?) GROUP BY ip HAVING hit >= ?";

        try {
            con = DBConnection.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, startDateTime.toString());
            ps.setString(2, endDateTime.toString());
            ps.setInt(3, threshold);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()){
                ips.add(resultSet.getString("ip"));
            }

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

        return ips;
    }
}
