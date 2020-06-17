package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order.Common.Table;
import com.example.order.Http.HttpUtil;
import com.example.order.Http.JsonData;
import com.example.order.R;
import com.example.order.adapter.TableAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TableActivity extends AppCompatActivity {
    @Bind(R.id.btn_left)
    ImageButton left;
    @Bind(R.id.tv_title)
    TextView title;
    private RecyclerView re_table;
    List<Table> tableList;
    Handler  handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        ButterKnife.bind(this);
        datainit();
        init();
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("选择餐桌");
    }
    private void datainit()
    {
        tableList=new ArrayList<>();
        String url="http://www.luckyc.top/Order/getfreetable";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(TableActivity.this,"连接服务器失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                Log.d("dsfdsg",responseText);
                tableList= JsonData.handleTableResponse(responseText);
                Log.d("fds",String.valueOf(tableList.size()));
                handler.sendEmptyMessage(0);
            }
        });
//        tableList=new ArrayList<>();
//        for(int i=0;i<=10;i++){
//            table r1=new table("5号桌","6人");
//            tableList.add(r1);
//
//        }
    }
    private void init() {
        handler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                // 工作线程中要发送的信息全都被放到了Message对象中，也就是上面的参数msg中。要进行操作就要先取出msg中传递的数据。
                switch (msg.what) {
                    case 0:
                        re_table=findViewById(R.id.re_table);
                        LinearLayoutManager manager = new LinearLayoutManager(TableActivity.this);
                        re_table.setLayoutManager(manager);
                        TableAdapter adapter = new TableAdapter(tableList);
                        re_table.setAdapter(adapter);
                        adapter.setOnItemClickListener(new TableAdapter.OnItemClickListener() {
                            @Override
                            public void onItemcardClick(int pos, List<Table> tableLists) {
                                Table t=tableLists.get(pos);
                                Intent intent =new Intent(TableActivity.this,ProductActivity.class);
                                intent.putExtra("tableId",String.valueOf(t.getId()));
                                Toast.makeText(TableActivity.this,String.valueOf(t.getId()),Toast.LENGTH_SHORT).show();
                                startActivityForResult(intent,2);
                            }
                        });
                        break;
                }
            }
        };
//        re_table=findViewById(R.id.re_table);
//        LinearLayoutManager manager = new LinearLayoutManager(TableActivity.this);
//        re_table.setLayoutManager(manager);
//        TableAdapter adapter = new TableAdapter(tableList);
//        re_table.setAdapter(adapter);
//        adapter.setOnItemClickListener(new TableAdapter.OnItemClickListener() {
//            @Override
//            public void onItemcardClick(int pos, List<Table> tableLists) {
//                Table t=tableLists.get(pos);
//                Intent intent =new Intent(TableActivity.this,ProductActivity.class);
//                intent.putExtra("tableId",t.getId());
//                Toast.makeText(TableActivity.this,t.getId(),Toast.LENGTH_SHORT).show();
//                startActivityForResult(intent,2);
//            }
//        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 2:
                if (resultCode == RESULT_OK) {
                    finish();
                }
                break;
        }
    }
}
