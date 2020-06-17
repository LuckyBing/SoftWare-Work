package com.example.order.Common;

public class Dishes {
    private String ID;     //id
    private String foodName;   //菜名
    private double foodPrice;   //价格
    private String note;    //备注
    private String evaluate;    //评价
    private int kindid;    //种类
    private int salesCount;     //菜品数量

    public double getFoodPrice() {
        return foodPrice;
    }

    public int getKindid() {
        return kindid;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getID() {
        return ID;
    }

    public String getNote() {
        return note;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setKindid(int kindid) {
        this.kindid = kindid;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

}
