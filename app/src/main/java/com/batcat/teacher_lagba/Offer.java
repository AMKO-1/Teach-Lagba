package com.batcat.teacher_lagba;

public class Offer {
    public String title;
    public String id;
    public String cls;
    public String rate;
    public String des;

    Offer() {

    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setCls(String cls) {
        this.cls = cls;
    }
    public void setRate(String rate) {
        this.rate = rate;
    }
    public void setDes(String des) {
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getCls() {
        return cls;
    }

    public String getRate() {
        return rate;
    }

    public String getDes() {
        return des;
    }
}