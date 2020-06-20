package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.order.Common.Table;
import com.example.order.Http.HttpUtil;
import com.example.order.Http.JsonData;
import com.example.order.R;
import com.example.order.adapter.DishAdapter;
import com.example.order.adapter.TableAdapter;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ViewActivity extends AppCompatActivity {
    @Bind(R.id.re_view)
    RecyclerView re_view;
    @Bind(R.id.btn_left)
    ImageButton left;
    @Bind(R.id.tv_title)
    TextView title;
    SharedPreferences pref;
    int pos;
    int state;
    List<Table> tableList;
    DishAdapter adapter;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        ButterKnife.bind(this);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("查看菜单");
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        Intent intent=getIntent();
        pos=Integer.valueOf(intent.getStringExtra("pos")).intValue();
        state=Integer.valueOf(intent.getStringExtra("state")).intValue();
        init();
        initview();

    }
    public void init()
    {
        if(state==1) {
            String url = "https://www.luckyc.top/Order/FindallOrderbycus_id?id=" + pref.getString("account", "") + "&state=1";
            HttpUtil.sendOkHttpRequest(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    tableList = JsonData.handleDResponse(responseText, pos);
                    handler.sendEmptyMessage(0);

                }
            });
        }
        else
        {
            String url = "https://www.luckyc.top/Order/FindallOrderbycus_id?id=" + pref.getString("account", "") + "&state=0";
            HttpUtil.sendOkHttpRequest(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    tableList = JsonData.handleDResponse(responseText, pos);
                    handler.sendEmptyMessage(0);

                }
            });
        }
    }
    public void initview()
    {
        handler=new Handler() {
            public void handleMessage(android.os.Message msg) {
                // 工作线程中要发送的信息全都被放到了Message对象中，也就是上面的参数msg中。要进行操作就要先取出msg中传递的数据。
                switch (msg.what) {
                    case 0:
                        LinearLayoutManager manager = new LinearLayoutManager(ViewActivity.this);
                        re_view.setLayoutManager(manager);
                        adapter = new DishAdapter(tableList);
                        re_view.setAdapter(adapter);
                        break;
                }
            }
        };

    }

}
