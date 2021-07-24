package com.example.finalproject;

public class User {
    String userid;
    String catName;
    String aomtNum;
    String dateIp;

    public User() {
    }

    public User(String userid, String catName, String aomtNum, String dateIp) {
        this.userid = userid;
        this.catName = catName;
        this.aomtNum = aomtNum;
        this.dateIp = dateIp;
    }

    public String getUserid() {
        return userid;
    }

    public String getCatName() {
        return catName;
    }

    public String getAomtNum() {
        return aomtNum;
    }

    public String getDateIp() {
        return dateIp;
    }
}