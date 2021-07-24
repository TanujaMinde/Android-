package com.example.finalproject;

public class UserData {
    String email,cat,amt,date;

    public UserData() {
    }

    public UserData(String eamil, String cat, String amt, String date) {
        this.email = eamil;
        this.cat = cat;
        this.amt = amt;
        this.date = date;
    }

    public String getEamil() {
        return email;
    }

    public void setEamil(String eamil) {
        this.email = eamil;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
