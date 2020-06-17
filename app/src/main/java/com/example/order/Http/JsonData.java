package com.example.order.Http;

import android.util.Log;

import com.example.order.Common.AllMenu;
import com.example.order.Common.Dishes;
import com.example.order.Common.Product;
import com.example.order.Common.Table;
import com.example.order.Common.tList;
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
                    item.setProductId(Integer.valueOf(food.getID()).intValue());
                    item.setType(dataResult.getResults().get(i).getTitle());
                    item.setFoodName(food.getFoodName());
                    item.setFoodPrice(food.getFoodPrice());
                    item.setSalesCount(food.getSalesCount());
                    item.setImageUrl("1");
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
}
