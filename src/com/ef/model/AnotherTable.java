package com.ef.model;

/**
 * Created by umarwhk(umrwhk@gmail.com) on 2019-06-03.
 */
public class AnotherTable {

    String ip;
    String comment;

    public AnotherTable() {
    }

    public AnotherTable(String ip, String comment) {
        this.ip = ip;
        this.comment = comment;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
