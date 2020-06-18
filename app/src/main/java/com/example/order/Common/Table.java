package com.example.order.Common;

public class Table {

    private String id;
    private String num;
    private int state;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public String getId() {
        return id;
    }
}