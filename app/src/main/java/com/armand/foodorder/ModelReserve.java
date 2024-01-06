package com.armand.foodorder;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ModelReserve {

    private  int id;
    private  int year;
    private  int month;
    private  int day;
    private  int hour;
    private  int min;
    private  int num;
    private String username;



    public ModelReserve(int id, int year, int month, int day, int hour, int min, int num, String username) {
        this.id = id;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.num = num;
        this.username = username;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
