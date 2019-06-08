package com.ef;

import com.ef.config.DBConnection;
import com.ef.model.AccessLog;
import com.ef.model.AnotherTable;
import com.ef.repository.AccessLogRepo;
import com.ef.repository.AnotherTableRepo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Parser {

    public static void main(String[] args) {
        DBConnection.getConnection();
        String startDate = "";
        String duration = "";
        Integer threshold = 0;
        String fileLoc = "";
        List<AccessLog> logs = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss");
        DateTimeFormatter logFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        LocalDateTime startDateTime = null,
                endDateTime = null;

        for (String arg: args) {
            if(arg.contains("startDate")){
                startDate = arg.split("=") [1];
                startDateTime = LocalDateTime.parse(startDate, formatter);
            }

            if(arg.contains("duration")){
                duration = arg.split("=") [1];
                if(duration.equalsIgnoreCase("hourly")){
                    endDateTime = startDateTime.plusHours(1L);
                }else if(duration.equalsIgnoreCase("daily")){
                    endDateTime = startDateTime.plusDays(1L);
                }
            }

            if(arg.contains("accesslog")){
                fileLoc = arg.split("=") [1];
            }

            if(arg.contains("threshold")){
                threshold = Integer.parseInt(arg.split("=") [1]);
            }
        }

        if(fileLoc.length() > 0){
            try(BufferedReader br = new BufferedReader(new FileReader(fileLoc))) {
                String line = "";
                while ((line = br.readLine()) != null) {
                    String[] logString = line.split(Pattern.quote("|"));

                    AccessLog log = new AccessLog(LocalDateTime.parse(logString[0], logFormatter),
                            logString[1],
                            logString[2],
                            Integer.parseInt(logString[3]),
                            logString[4]);

                    AccessLogRepo.save(log);
                }

                List<String> ipExceed = AccessLogRepo.ipExceedThreshold(startDateTime, endDateTime, threshold);
                for (String ip: ipExceed) {
                    String comment = "This IP made more than "+threshold+" requests starting from "+startDateTime.toString()+" to "+endDateTime.toString();
                    AnotherTable anotherTable = new AnotherTable(ip, comment);
                    AnotherTableRepo.save(anotherTable);
                    System.out.println(ip);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
