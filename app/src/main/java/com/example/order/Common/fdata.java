package com.example.order.Common;

import java.util.List;

public class fdata {
    private String inf;
    private List<oList> orderList;

    public void setInf(String info) {
        this.inf = info;
    }

    public String getInf() {
        return inf;
    }

    public List<oList> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<oList> orderList) {
        this.orderList = orderList;
    }
    public static class oList{
        private String order_id;
        private String table_id;
        private String overdate;
        private String priceall;
        private String dushes_num;
        private List<dish> dishes;

        public String getOverdate() {
            return overdate;
        }

        public void setOverdate(String overdate) {
            this.overdate = overdate;
        }

        public void setDishes(List<dish> dishes) {
            this.dishes = dishes;
        }

        public List<dish> getDishes() {
            return dishes;
        }

        public String getDushes_num() {
            return dushes_num;
        }

        public String getOrder_id() {
            return order_id;
        }

        public String getPriceall() {
            return priceall;
        }

        public String getTable_id() {
            return table_id;
        }

        public void setDushes_num(String dushes_num) {
            this.dushes_num = dushes_num;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public void setPriceall(String priceall) {
            this.priceall = priceall;
        }

        public void setTable_id(String table_id) {
            this.table_id = table_id;
        }

    }
    public static class dish{
        private String name;
        private String num;

        public void setNum(String num) {
            this.num = num;
        }

        public String getName() {
            return name;
        }

        public String getNum() {
            return num;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
