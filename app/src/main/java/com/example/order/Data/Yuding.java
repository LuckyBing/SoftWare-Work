package com.example.order.Data;

/**
 * Created by 87310 on 2019/2/15.
 */

public class Yuding {

    private boolean ispingjia;
    private String ordertime;
    private String mrname;
    private String meettime;
    private String meetname;
    private int state;

    public Yuding(String ordertime, String mrname, String meettime, String meetname, int state) {
        this.ordertime = ordertime;
        this.mrname = mrname;
        this.meettime = meettime;
        this.meetname = meetname;
        this.state = state;
    }

    public boolean ispingjia() {
        return ispingjia;
    }

    public void setIspingjia(boolean ispingjia) {
        this.ispingjia = ispingjia;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getMrname() {
        return mrname;
    }

    public void setMrname(String mrname) {
        this.mrname = mrname;
    }

    public String getMeettime() {
        return meettime;
    }

    public void setMeettime(String meettime) {
        this.meettime = meettime;
    }

    public String getMeetname() {
        return meetname;
    }

    public void setMeetname(String meetname) {
        this.meetname = meetname;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
