package com.example.order.Http;

import android.util.Log;

import com.example.order.Common.AllMenu;
import com.example.order.Common.Dishes;
import com.example.order.Common.Product;
import com.example.order.Common.Table;
import com.example.order.Common.fdata;
import com.example.order.Common.tList;
import com.example.order.Data.Yuding;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class JsonData {
    public static List<Table> handleTableResponse(String response) {
        try {
            tList tlist = new Gson().fromJson(response, tList.class);
            Log.d("dsf",tlist.getInf());
            return tlist.getTablelist();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Product> handleMenuResponse(String response){
        try {
            AllMenu dataResult = new Gson().fromJson(response, AllMenu.class);
            List<Product> products=new ArrayList<Product>();
            for (int i=0;i<dataResult.getResults().size();i++){
                for (int j=0;j<dataResult.getResults().get(i).getDishes().size();j++){
                    Dishes food=dataResult.getResults().get(i).getDishes().get(j);
                    Product item=new Product();
                    Log.d("fs",food.getID());
                    item.setProductId(Integer.valueOf(food.getID()).intValue());
                    item.setType(dataResult.getResults().get(i).getTitle());
                    item.setFoodName(food.getFoodName());
                    item.setFoodPrice(food.getFoodPrice());
                    item.setSalesCount(food.getSalesCount());
                    item.setImageUrl(food.getUrl());
                    item.setSeleteId(i);
                    products.add(item);
                }
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Yuding> handleYResponse(String response,int state){
        try {
            fdata dataResult = new Gson().fromJson(response, fdata.class);
            List<Yuding> products=new ArrayList<Yuding>();
            for (int i=0;i<dataResult.getOrderList().size();i++){

                    fdata.oList food=dataResult.getOrderList().get(i);
                    Yuding item=new Yuding();
                    item.setMeettime(food.getOverdate());
                    item.setOrdertime(food.getOrder_id());
                    item.setMrname(food.getTable_id());
                    item.setMeetname(food.getPriceall());
                    item.setState(state);
                    products.add(item);
                }

            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<Table> handleDResponse(String response,int pos)
    {
        try {
            fdata dataResult = new Gson().fromJson(response, fdata.class);
            List<Table> products = new ArrayList<Table>();
            for (int i = 0; i < dataResult.getOrderList().get(pos).getDishes().size(); i++) {
                fdata.dish food = dataResult.getOrderList().get(pos).getDishes().get(i);
                Table item = new Table();
                item.setId(food.getName());
                item.setNum(food.getNum());
                item.setState(0);
                Log.d("dfsfs",item.getNum());
                products.add(item);
            }
                return products;
            } catch(Exception e){
                e.printStackTrace();
            }

        return null;
    }

}
