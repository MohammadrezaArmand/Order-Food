package com.armand.foodorder;

public class ModelNeg {

    private  int id;
    private String subject;
    private String priceneg;
    private String date;



    public ModelNeg(int id, String subject, String priceneg, String date) {
        this.id = id;
        this.subject = subject;
        this.priceneg = priceneg;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPriceneg() {
        return priceneg;
    }

    public void setPriceneg(String priceneg) {
        this.priceneg = priceneg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
