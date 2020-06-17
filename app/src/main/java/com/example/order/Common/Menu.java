package com.example.order.Common;

import java.util.List;

public class Menu {
    private int _id;
    private String title;
    public List<Dishes> dishes;

    public int get_id() {
        return _id;
    }

    public List<Dishes> getDishes() {
        return dishes;
    }

    public String getTitle() {
        return title;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setDishes(List<Dishes> dishes) {
        this.dishes = dishes;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
