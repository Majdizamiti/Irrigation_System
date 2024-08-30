package com.example.irrigation_system;

public class Hourly {
    private String hour ;
    private int temp ;
    private String picpath ;

    public Hourly(String hour, int temp, String picpath) {
        this.hour = hour;
        this.temp = temp;
        this.picpath = picpath;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }
}
