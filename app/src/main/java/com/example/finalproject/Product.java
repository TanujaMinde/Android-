package com.example.finalproject;

public class Product {
    String eamil,cat,amt,date;

    public Product() {

    }

    public Product(String eamil, String cat, String amt, String date) {
        this.eamil = eamil;
        this.cat = cat;
        this.amt = amt;
        this.date = date;
    }

    public String getEamil() {
        return eamil;
    }

    public void setEamil(String eamil) {
        this.eamil = eamil;
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
