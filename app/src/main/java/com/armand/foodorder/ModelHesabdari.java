package com.armand.foodorder;

public class ModelHesabdari {

    private  int id;
    private String username;
    private String allprice;
    private String pay;

    public ModelHesabdari (int id,  String username, String allprice, String pay) {
        this.id = id;

        this.username = username;
        this.allprice = allprice;
        this.pay = pay;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getAllprice() {return allprice;}

    public void setAllprice(String allprice) {this.allprice = allprice;}
}
