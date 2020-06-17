package com.example.order.Common;

import java.util.ArrayList;
import java.util.List;

public class tList {
    private String inf;
    private ArrayList<Table> tablelist;

    public void setTablelist(ArrayList<Table> tablelist) {
        this.tablelist = tablelist;
    }

    public ArrayList<Table> getTablelist() {
        return tablelist;
    }

    public String getInf() {
        return inf;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }
}
