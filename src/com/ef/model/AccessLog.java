package com.ef.model;

import java.time.LocalDateTime;

/**
 * Created by umarwhk(umrwhk@gmail.com) on 2019-06-03.
 */
public class AccessLog {

    LocalDateTime dateTime;
    String ip;
    String request;
    Integer requestStatus;
    String userAgent;

    public AccessLog() {
    }

    public AccessLog(LocalDateTime dateTime, String ip, String request, Integer requestStatus, String userAgent) {
        this.dateTime = dateTime;
        this.ip = ip;
        this.request = request;
        this.requestStatus = requestStatus;
        this.userAgent = userAgent;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Integer getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
