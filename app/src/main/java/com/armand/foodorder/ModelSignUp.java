package com.armand.foodorder;

public class ModelSignUp {

    private  int id;
    private String name;
    private String mobile;
    private String pass;



    public ModelSignUp(int id,  String name, String mobile, String pass) {
        this.id = id;

        this.name = name;
        this.mobile = mobile;
        this.pass = pass;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
