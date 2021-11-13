package com.example.myapplication.model;

public class Book {
    String IMAGE;
    String TITLE;
    String AUTHOR;
    String TYPENAME;
    String PRICE;
    String INTRODUCTION;
    int PAGE;
    String NEWBOOK;

    public Book() {
    }

    public Book(String IMAGE, String TITLE, String AUTHOR, String TYPENAME, String PRICE, String INTRODUCTION, int PAGE , String NEWBOOK) {
        this.IMAGE = IMAGE;
        this.TITLE = TITLE;
        this.AUTHOR = AUTHOR;
        this.TYPENAME = TYPENAME;
        this.PRICE = PRICE;
        this.INTRODUCTION = INTRODUCTION;
        this.PAGE = PAGE;
        this.NEWBOOK = NEWBOOK;
    }

    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getAUTHOR() {
        return AUTHOR;
    }

    public void setAUTHOR(String AUTHOR) {
        this.AUTHOR = AUTHOR;
    }

    public String getTYPENAME() {
        return TYPENAME;
    }

    public void setTYPENAME(String TYPENAME) {
        this.TYPENAME = TYPENAME;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public String getINTRODUCTION() {
        return INTRODUCTION;
    }

    public void setINTRODUCTION(String INTRODUCTION) {
        this.INTRODUCTION = INTRODUCTION;
    }

    public int getPAGE() {
        return PAGE;
    }

    public void setPAGE(int PAGE) {
        this.PAGE = PAGE;
    }

    public String getNEWBOOK() {
        return NEWBOOK;
    }

    public void setNEWBOOK(String NEWBOOK) {
        this.NEWBOOK = NEWBOOK;
    }
}
