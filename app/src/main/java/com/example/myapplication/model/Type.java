package com.example.myapplication.model;

public class Type {
    String AUTHOR, IMAGE, INTRODUCTION, PRICE, TITLE, TYPENAME;
    Integer PAGE;

    public Type() {
    }

    public Type(String AUTHOR, String IMAGE, String INTRODUCTION, String PRICE, String TITLE, String TYPENAME, Integer PAGE) {
        this.AUTHOR = AUTHOR;
        this.IMAGE = IMAGE;
        this.INTRODUCTION = INTRODUCTION;
        this.PRICE = PRICE;
        this.TITLE = TITLE;
        this.TYPENAME = TYPENAME;
        this.PAGE = PAGE;
    }

    public String getAUTHOR() {
        return AUTHOR;
    }

    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    public String getINTRODUCTION() {
        return INTRODUCTION;
    }

    public void setINTRODUCTION(String INTRODUCTION) {
        this.INTRODUCTION = INTRODUCTION;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getTYPENAME() {
        return TYPENAME;
    }

    public void setTYPENAME(String TYPENAME) {
        this.TYPENAME = TYPENAME;
    }

    public Integer getPAGE() {
        return PAGE;
    }

    public void setPAGE(Integer PAGE) {
        this.PAGE = PAGE;
    }
}