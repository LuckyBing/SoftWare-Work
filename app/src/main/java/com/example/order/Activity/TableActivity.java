package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.order.Common.table;
import com.example.order.R;
import com.example.order.adapter.TableAdapter;

import java.util.ArrayList;
import java.util.List;

public class TableActivity extends AppCompatActivity {
    private RecyclerView re_table;
    List<table> tableList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        datainit();
        init();
    }
    private void datainit()
    {
        tableList=new ArrayList<>();
        for(int i=0;i<=10;i++){
            table r1=new table("5号桌","6人");
            tableList.add(r1);

        }
    }
    private void init() {
        re_table=findViewById(R.id.re_table);
        LinearLayoutManager manager = new LinearLayoutManager(TableActivity.this);
        re_table.setLayoutManager(manager);
        TableAdapter adapter = new TableAdapter(tableList);
        re_table.setAdapter(adapter);
    }
}
