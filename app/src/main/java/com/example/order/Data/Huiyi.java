package com.example.order.Data;

/**
 * Created by 87310 on 2019/2/17.
 */

public class Huiyi {
    private int type;
    private String name;
    private String time;
    private int state;
    private String fuzeren;
    private String place;

    public Huiyi(int type, String name, String time) {
        this.type = type;
        this.name = name;
        this.time = time;
    }

    public String getFuzeren() {
        return fuzeren;
    }

    public void setFuzeren(String fuzeren) {
        this.fuzeren = fuzeren;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getType() {
        return type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
