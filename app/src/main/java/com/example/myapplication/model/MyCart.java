package com.example.myapplication.model;

public class MyCart {
    String TITLE, TOTALQUANTITY, CURRENTDATE, CURRENTTIME, IMAGE;
    int TOTALPRICE;

    public MyCart() {
    }

    public MyCart(String TITLE, String TOTALQUANTITY, String CURRENTDATE, String CURRENTTIME, String IMAGE, int TOTALPRICE) {
        this.TITLE = TITLE;
        this.TOTALQUANTITY = TOTALQUANTITY;
        this.CURRENTDATE = CURRENTDATE;
        this.CURRENTTIME = CURRENTTIME;
        this.IMAGE = IMAGE;
        this.TOTALPRICE = TOTALPRICE;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getTOTALQUANTITY() {
        return TOTALQUANTITY;
    }

    public void setTOTALQUANTITY(String TOTALQUANTITY) {
        this.TOTALQUANTITY = TOTALQUANTITY;
    }

    public String getCURRENTDATE() {
        return CURRENTDATE;
    }

    public void setCURRENTDATE(String CURRENTDATE) {
        this.CURRENTDATE = CURRENTDATE;
    }

    public String getCURRENTTIME() {
        return CURRENTTIME;
    }

    public void setCURRENTTIME(String CURRENTTIME) {
        this.CURRENTTIME = CURRENTTIME;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    public int getTOTALPRICE() {
        return TOTALPRICE;
    }

    public void setTOTALPRICE(int TOTALPRICE) {
        this.TOTALPRICE = TOTALPRICE;
    }
}