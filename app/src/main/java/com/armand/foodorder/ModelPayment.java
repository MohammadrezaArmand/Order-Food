package com.armand.foodorder;

public class ModelPayment {

    private  int id;
    private String username;
    private String allprice;
    private String delivery;


    public ModelPayment(int id, String username, String allprice, String delivery) {
        this.id = id;
        this.username = username;
        this.allprice = allprice;
        this.delivery = delivery;

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAllprice() {
        return allprice;
    }

    public void setAllprice(String allprice) {
        this.allprice = allprice;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
