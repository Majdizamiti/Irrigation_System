package com.example.irrigation_system;

public class FutureDomain {
    private String day;
    private String picpath;
    private String status;
    private int hightemp;
    private int lowtemp ;

    public FutureDomain(String day, String picpath, String status, int hightemp, int lowtemp) {
        this.day = day;
        this.picpath = picpath;
        this.status = status;
        this.hightemp = hightemp;
        this.lowtemp = lowtemp;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getHightemp() {
        return hightemp;
    }

    public void setHightemp(int hightemp) {
        this.hightemp = hightemp;
    }

    public int getLowtemp() {
        return lowtemp;
    }

    public void setLowtemp(int lowtemp) {
        this.lowtemp = lowtemp;
    }
}
