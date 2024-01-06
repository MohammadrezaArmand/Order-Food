package com.armand.foodorder;

public class Model {

    private  int id;
    private byte[]prov;
    private String username;
    private String price;
    private String code;
    private String calori;


    public Model(int id, byte[] prov, String username, String price, String code, String calori) {
        this.id = id;
        this.prov = prov;
        this.username = username;
        this.price = price;
        this.code = code;
        this.calori = calori;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getProv() {
        return prov;
    }

    public void setProv(byte[] prov) {
        this.prov = prov;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCalori() {
        return calori;
    }

    public void setCalori(String calori) {
        this.calori = calori;
    }
}
